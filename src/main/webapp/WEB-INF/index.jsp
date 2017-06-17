<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/bootstrap.css">
    <title>Title</title>
    <style></style>
</head>
<body>
    <table class="table table-striped table-bordered" id="tab">
        <thead>
            <tr>
                <td>NAME</td>
                <td>AGE</td>
                <td>年级</td>
                <td>修改</td>
            </tr>
        </thead>
        <tbody>

        </tbody>
    </table>

    <div id="d1">
        <form class="navbar-form navbar-left" role="search">
            <div class="form-group">
                <input type="text" class="form-control" id="sname" placeholder="姓名">
                <input type="number"  class="form-control" id="sage" placeholder="年龄">
                <input type="text" class="form-control" id="sdept" placeholder="班级">
            </div>
            <button type="button" class="btn btn-default">添加</button>
        </form>
    </div>


</body>
</html>