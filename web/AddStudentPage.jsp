<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 13.04.2016
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Model.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Лабораторная работа HTML</title>
    <style>
        html, body{	margin:0;
            padding:0;
            text-align:center;
        }
        #general_box{ width:900px;
            height: 200px;
            border:0px dotted;
            margin: 20px auto 0;
        }
        #Header { position: absolute;
            height: 60px;
            line-height: 60px;
            text-align: center;
            color: #99D3FF;
            top: 70px;
            background-color:	#transparent;
        }
        #Header h1{
            margin-left:450px;
        }
        #Navigation{ height: 30px;
            line-height:30px;
            text-align: left;
            text-indent: 20px;
            color: White;
            background-color: #4b4b4b;
        }
        #NavigationLeft{
            width: 200px;
            background-color: #F8F8F6;
            height: 250px;

            float: left;
            text-align: left;
            margin: 5px;
        }
        #Form_1{ margin: 10px;
        }
        #Content { vertical-align: top;
            margin:10px;
            font: 14px Arial, Verdana, sans-serif;
            text-align: left;
        }
        #Footer {height:25px;
            margin-top:25px;
            font:14px Arial, Verdana, sans-serif;
            background-color: #dcdad8;
        }
        table {
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            font-size: 14px;
            border-collapse: collapse;
            text-align: center;
            margin: auto;
        }
        th, td:first-child {
            background: #AFCDE7;
            color: white;
            padding: 10px 20px;
        }
        th, td {
            border-style: solid;
            border-width: 0 1px 1px 0;
            border-color: white;
        }
        td {
            background: #D8E6F3;
        }
        th:first-child, td:first-child {
            text-align: left;
        }
        a{ color: #233A3E;
            margin: 5%;
        }
        a:active{ color:#1E90FF;
        }

    </style>
</head>
<body>
<div id="general_box">
    <img src="http://i11.pixs.ru/storage/5/0/3/logojpg_2994482_21524503.jpg" alt="Система обработки списка сотрудников"/>
    <div id="Header">
        <h1>Лабораторная работа HTML</h1>
    </div>
    <div id="Navigation"><center><h3>Добавление студента в группу</h3></center></div>
    <div id="NavigationLeft">
        <p><a href="/all-groups">Список всех групп</a></p>
        <p><a href="/">Cписок всех студентов</a></p>
        <p><a href="/AddStudentPage.jsp">Добавить студента</a></p>
        <p><a href="/AddGroupPage.jsp">Добавить группу</a></p>
        <p><a href="/redact-student-page">Редактирование студентов</a></p>
        <p><a href="/delete-student-page">Удаление студентов</a></p>
        <p><a href="/redact-group-page">Редактирование групп</a></p>
    </div>


    <div id="Content">
        <h3>Заполните форму для добавления нового студента</h3>
        <div id="Form_1">
            <form action="/add-student-page" method="Post">
                <table align="center">
                    <tr><th colspan="3" align="center">Добавление студента</th></tr>
                    <tr><td align="right">ID</td><td align ="left"><input type = "text" name="id_of_student"/></td></tr>
                    <tr><td align="right">Имя</td><td align ="left"><input type = "text" name="name_of_student"/></td></tr>
                    <tr><td align="right">Фамилия</td><td align ="left"><input type = "text" name="surname_of_student"/></td></tr>
                    <tr><td align="right">Группа</td><td align ="left"><input type = "text" name="group_of_student"/></td></tr>
                    <tr><td align="right">Дата</td><td align ="left"><input type = "text" name="date_of_student"/></td></tr>
                    <tr><td></td><td colspan="1" align="center"><input type="submit" name="add_button" value="Добавить"/></td><td></td></tr>
                </table>
            </form>
        </div>
    </div>
    <div id="Footer"><font size="-2">Copyright &copy; 2016, Лебедев Павел.</font></div>
</div>


</body>
</html>
