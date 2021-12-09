FROM node:12.7-alpine AS build
WORKDIR /usr/src/app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build


FROM nginx:1.17.1-alpine
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=build /usr/src/app/dist/ui /usr/share/nginx/html
CMD ["/bin/sh",  "-c",  "envsubst < /usr/share/nginx/html/assets/app-config.prod.json > /usr/share/nginx/html/assets/app-config.json && exec nginx -g 'daemon off;'"]


