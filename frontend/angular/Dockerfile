
FROM node:lts

WORKDIR /usr/src/app/

RUN npm install -g @angular/cli @angular-devkit/build-angular && npm install

EXPOSE 4200

CMD ng serve --host 0.0.0.0