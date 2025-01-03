package customtype;

import enums.Gender;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserTypeSupport;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class GenderType extends UserTypeSupport<Gender> {

    public GenderType() {
        super(Gender.class, Types.CHAR); // Định nghĩa kiểu Java và kiểu JDBC CHAR
    }

    @Override
    public Gender nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session) throws SQLException {
        String code = rs.getString(position);
        return (code != null && code.trim().length() == 1) ? Gender.fromCode(code.charAt(0)) : null;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Gender value, int index, SharedSessionContractImplementor session) throws SQLException {
        st.setString(index, value != null ? String.valueOf(value.getCode()) : null); // Gán mã (M/F) vào cột
    }
}

