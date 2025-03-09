FROM openjdk:21
WORKDIR /app
COPY build/libs/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
EXPOSE 3306
EXPOSE 27017
CMD ["java", "-jar", "app.jar"]

FROM node:20
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build
EXPOSE 3000
CMD ["npm", "run", "start"]

FROM nginx:alpine
COPY --from=backend /app/backend/build/libs/app.jar /app.jar
COPY --from=frontend /app/frontend/dist /usr/share/nginx/html