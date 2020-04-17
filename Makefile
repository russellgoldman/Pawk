make build:
	# build and push postgraphile image to container registry
	docker build -t gcr.io/pawk-274505/postgraphile ./graphql
	docker push gcr.io/pawk-274505/postgraphile

make deploy:
	# respond y to allow unauthenticated invocations.
	gcloud beta run deploy --image gcr.io/pawk-274505/postgraphile --add-cloudsql-instances postgres --update-env-vars DB_HOST=/cloudsql/pawk-274505:us-central1:postgres, postgraphile 