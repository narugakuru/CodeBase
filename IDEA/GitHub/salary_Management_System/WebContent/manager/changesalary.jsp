<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String name = request.getParameter("name");
		String basicsalary = request.getParameter("basicsalary");
		String allowance = request.getParameter("allowance");
		String bonous = request.getParameter("bonous");
		String overtimepay = request.getParameter("overtimepay");
		String totalsalary = request.getParameter("totalsalary");

		String driverClass = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/salary";
		String username = "root";
		String password = "root";
		Class.forName(driverClass);//加载驱动 
		Connection connection = DriverManager.getConnection(url, username, password);
		String sql = "update allsalary "+"set name='" + name + "',basicsalary='" + basicsalary + "',allowance=" + allowance + ",bonous='"
				+ bonous + "',overtimepay='" + overtimepay + "',totalsalary='" + totalsalary + "' WHERE name='" + name + "';";
		System.out.print(sql);
		Statement createtable = connection.createStatement();
		try{
			createtable.execute(sql);
			out.println("<script>alert('修改工资信息成功！');window.location.href='salarycheck.jsp';</script>");					
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	%>
</body>
</html>