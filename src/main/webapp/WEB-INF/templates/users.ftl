<!DOCTYPE html>
<html>
<head>
    <title>Users</title>
</head>
<body>
    <ul>
        <#if users?has_content>
        <#list users as user>
            <li>${user.name}, ${user.surname}, ${user.email}</li>
        </#list>
        <#else>
            <p>no users yet</p>
        </#if>

    </ul>
</body>
</html>