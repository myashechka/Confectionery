cmd /C mvnw clean package -Dmaven.test.skip -f pom.xml
cmd /C docker image rm muashechka/bamell:1.0
cmd /C docker build -t muashechka/bamell:1.0 .
cmd /C docker push muashechka/bamell:1.0
