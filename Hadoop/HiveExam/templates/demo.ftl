<html>
<head>
    <title>Welcome!</title>
</head>
<body>
<#assign info='来自模版自己的声明' seflag=true >
<#if flag >
    Flag is ${info}
<#elseif seflag >
    模版内布尔值为false
<#else>
    Flag and seflag is False
</#if>
</body>
</html>