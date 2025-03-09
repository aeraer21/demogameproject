FROM openjdk:21 as backend
WORKDIR /app
COPY demo/build/libs/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
EXPOSE 3306
EXPOSE 27017
CMD ["java", "-jar", "app.jar"]

FROM node:20 as frontend
WORKDIR /app
COPY nextdemo/package.json nextdemo/package-lock.json ./
RUN npm install
COPY . .
RUN npm run build
EXPOSE 3000
CMD ["npm", "run", "start"]

FROM nginx:alpine
COPY --from=backend /app/app.jar /app.jar
COPY --from=frontend /app/out /usr/share/nginx/html