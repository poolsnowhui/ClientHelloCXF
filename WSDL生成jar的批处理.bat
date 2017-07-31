md D:\spring\workspace\helloInterface\bin\
md D:\spring\workspace\helloInterface\src\

wsimport  -keep  -s D:\spring\workspace\helloInterface\src\ -d D:\spring\workspace\helloInterface\bin\ -verbose http://localhost:8080/helloCXF/webservice/HelloWorld?wsdl

cd D:\spring\workspace\helloInterface\bin\

jar -cvf helloInterface.jar com\


cd D:\spring\workspace\helloInterface\src\

jar -cvf helloInterface_source.jar com\


copy D:\spring\workspace\helloInterface\bin\helloInterface.jar D:\spring\workspace\ClientHelloCXF\WebContent\WEB-INF\lib
copy D:\spring\workspace\helloInterface\src\helloInterface_source.jar D:\spring\workspace\ClientHelloCXF\WebContent\WEB-INF\lib
pause