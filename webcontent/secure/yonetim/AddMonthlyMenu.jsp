<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-8">
<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
<script src="../js/Language.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
        	var select1 = document.getElementById("dosyaAdi");
        	var isim=select1.options[select1.selectedIndex].value;
        	var select2 = document.getElementById("dosyaUzanti");
        	var uzanti=select2.options[select2.selectedIndex].value;
        	$("#sec").attr('name' , isim + uzanti );
        	$("#dosyaAdi").change(function(){
        		var isim=select1.options[select1.selectedIndex].value;
            	$("#sec").attr('name' , isim + uzanti );
        	});
            $("#dosyaUzanti").change(function(){
            	var uzanti=select2.options[select2.selectedIndex].value;
            	$("#sec").attr('name' , isim + uzanti );
        	});
        	
        });
        </script>

        <style type="text/css">
        #yukleme{
        margin-left:20px;
        width:400px;
        }
        .btns{
        margin-left:450px; 
        height:300px;
        margin-right:auto; 
        margin-top:60px; 
        background:white;
        width:500px;
        }
           ul {
                 margin-left:100px;
                 list-style: none outside none;
                 margin-top:20px;          
              } 
              ul li {
                     display: inline;
                    position: relative;
                    padding:10px;
                    border: 1px solid Black;
                    margin-left:10px;
              }
        </style>
</head>
<body>
<br />
<center><h3 id="AddFile">Add Mountly Menu or Help Menu</h3>

<div class="btns"> <br />
    <div id="yukleme">  
   <span id="selectAfile"> Select a File Mountly Menu /Help Menu </span>
    <select id="dosyaAdi">
    <option value="AylikMenu" >Add a monthly menu</option>
    <option value="YardimMenu" >Add a Help Menu</option>
    </select><hr /><br />   
    <span id="SelectFileType">Select the Type Of File </span>
    <select id="dosyaUzanti">
           <option value=".xlsx">EXCEL</option>
         <!--  <option value=".docx">word</option>
           <option value=".pdf">PDF</option> --> 
        </select><hr />
    <div>
    <form action="../FileUpload" method="post"  enctype="multipart/form-data">
      <h3 id="SelectFile">Select the File</h3> 
      <input type="file" id="sec" size="50" /> <br/><br/>
    <input type="submit" id="AddtheFile" value="Add File" />   
    </form> <br/>
    </div>
    </div>
 </div>
<div id="onizleme" style="margin-top: 10px;"> </div>
</center>
</body>
</html>