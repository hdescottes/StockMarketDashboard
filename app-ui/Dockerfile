# Dockerfile pour le REACT JS (FRONTEND)

# Les insctructions sont les mêmes que pour le Dockerfile de l'API REST, sauf pour le port d'écoute qui change.

FROM node:lts-alpine

WORKDIR /app-ui

COPY ./package.json /app-ui

RUN yarn install

COPY . .

EXPOSE 3000

CMD [ "yarn", "start" ]