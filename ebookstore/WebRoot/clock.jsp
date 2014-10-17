<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
    <script type="text/javascript">

    	function start(){
    		setInterval("autoChange()",1);
    	}
    	function autoChange(){
			document.getElementById("clockImg").src = "<%=request.getContextPath() %>/autoClock?" + new Date();
        }
    
    </script>
  </head>
  <body onload="start();">
    <img id="clockImg" src="<%=request.getContextPath() %>/autoClock">
  </body>
</html>
