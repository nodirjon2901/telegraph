#proyektni nima orqali run qilayotganligini bildirmoqda
FROM openjdk:17
#proyekt dockerda qaysi portda run bo'lishini bildiradi
EXPOSE 8080
#proyekt jar file ni bitta o'zgaruvchiga olib qo'yish
ARG JAR_FILE=target/telegraph-clone-0.0.1-SNAPSHOT.jar
#proyektni o'zim tanlagan nom bilan qo'shyapman
ADD ${JAR_FILE} /my-project
#proyektni run qilish uchun commanda yozib qo'yamiz
ENTRYPOINT ["java", "-jar", "/my-project"]
