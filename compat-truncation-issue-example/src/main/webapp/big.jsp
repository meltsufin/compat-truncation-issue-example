<html>
<head><title>Big JSP</title></head>
<body>
  <%
    String scaleParam = request.getParameter("scale");
    int scale = 50;
    if (scaleParam != null)
        scale = Integer.parseInt(scaleParam);


    for (int i = 1; i <= scale; i++) {
        out.println("<h2>" + i + " of " + scale + "</h2>");
        out.flush();
        for (int j = 1; j <= scale; j++) {
            for (int k = 1; k <= scale; k++) {
                out.print(i + "-" + j + "-" + k + "|");
                out.flush();
            }
            out.println("</br>");
            out.flush();
        }
    }
  %>
</body>
</html>