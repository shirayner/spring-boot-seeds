<!DOCTYPE html>
<!--
  description  
  @author shira 2019/05/07 12:41 
-->
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> ${title} </title>
</head>
<body>
    <h1>${title}</h1>

    <table>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>age</th>
        </tr>
        <#list userList as user>
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.age}</td>
            </tr>
        </#list>
    </table>


</body>
</html>