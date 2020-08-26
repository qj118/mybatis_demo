<%@ page import="java.io.InputStream" %>
<%@ page import="org.apache.ibatis.io.Resources" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactoryBuilder" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page import="org.demon.dao.UserDao" %>
<%@ page import="org.demon.domain.User" %>
<%@ page import="java.util.List" %>
<html>
<body>
<h2>Hello World!</h2>
<%
    // 测试类
    InputStream in = Resources.getResourceAsStream("SqlConfig.xml");
    SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
    SqlSessionFactory factory = builder.build(in);
    SqlSession sqlSession = factory.openSession();
    UserDao userDao = sqlSession.getMapper(UserDao.class);
    List<User> users = userDao.findAll();
    for(User user: users){
        System.out.println(user);
    }
%>
</body>
</html>
