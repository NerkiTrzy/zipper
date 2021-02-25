<h3>PRZEMYSLAW ROGUSKI CODE TEST FOR CORREVATE</h3>

To run:
1. mvn clean install
2. docker build -t zipper .
3. docker run -it -p 8080:8080 --name zipper zipper
4. you can check the app status at: "http://localhost:8080/actuator/health"

Documentation of zip feature is available on Swagger:
http://localhost:8080/swagger-ui/index.html

and also on OpenAPI3.0 at: http://localhost:8080/api-docs/

I recommend to use Postman to test application. 