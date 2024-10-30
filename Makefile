ENV?=local.env
include $(ENV)

connect:
	psql postgresql://$(PG_USER):$(PG_PASSWORD)@localhost:$(PG_PORT)/$(PG_DB)
###############################################################################
#                                   Docker                                    #
###############################################################################

docker.postgres.start:
	#docker-compose up -d db
	docker-compose up db

docker.api.start:
	docker compose build
	#docker-compose up  api
	docker-compose up api

docker.api.run:
	docker compose build
	docker-compose run api

docker.all.stop:
	docker-compose down

docker.all.restart:
	docker-compose down
	docker compose build
	docker-compose up -d

docker.all.start:
	docker-compose up -d


