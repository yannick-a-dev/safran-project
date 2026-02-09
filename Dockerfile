# ==========================
# Build stage
# ==========================
FROM maven:3.9.1-eclipse-temurin-17 AS build
WORKDIR /app

# Copie pom.xml pour profiter du cache Docker
COPY pom.xml .

# Télécharge les dépendances (offline) pour accélérer le build
RUN mvn dependency:go-offline

# Copie le code source
COPY src ./src

# Compile l'application sans tests
RUN mvn clean package -DskipTests

# ==========================
# Run stage
# ==========================
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copie le jar compilé depuis le build
COPY --from=build /app/target/*.jar app.jar

# Variable optionnelle pour le profil Spring Boot
ENV SPRING_PROFILES_ACTIVE=prod

ENTRYPOINT ["java", "-jar", "app.jar"]
