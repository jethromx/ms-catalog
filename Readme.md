# Getting Started

### Reference Documentation


### Maven Parent overrides


### Mysql Local

podman compose up -d
docker-compose up -d


### SWAGGER OPEN API 3.0
<schema>://<url>:<port>/swagger-ui/index.html

### API DOCS

<schema>://<url>:<port>/v3/api-docs


docker build -t gcr.io/ms-catalog:0.0.1 .

docker push gcr.io/ms-catalog:0.0.1

gcloud run deploy cr-ms-catalog --image gcr.io/ms-catalog-registry/ms-catalog-db:0.0.1 --platform managed --region us-central1 --allow-unauthenticated