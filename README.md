# Instructions for Guice and 404 issue repro

```bash
git clone https://github.com/meltsufin/compat-truncation-issue-example.git
cd compat-truncation-issue-example
git checkout qos
```

## Remote Test

```
mvn clean appengine:deploy
project=[[your-gcp-project]]
url=https://$project.appspot.com/hello?delay=2000
./test_parallel.sh $url 5
```

You should see someting like this:

```
1: fetching https://example-project.appspot.com/hello?delay=2000
2: fetching https://example-project.appspot.com/hello?delay=2000
3: fetching https://example-project.appspot.com/hello?delay=2000
4: fetching https://example-project.appspot.com/hello?delay=2000
5: fetching https://example-project.appspot.com/hello?delay=2000
HTTP/1.1 200 OK
HTTP/1.1 404 Not Found
HTTP/1.1 404 Not Found
HTTP/1.1 404 Not Found
HTTP/1.1 200 OK
```

## Local Test

```
mvn jetty:run
url=http://localhost:8080/hello?delay=2000
./test_parallel.sh $url 3
```

You should see something like this:

```
1: fetching http://localhost:8080/hello?delay=2000
2: fetching http://localhost:8080/hello?delay=2000
3: fetching http://localhost:8080/hello?delay=2000
HTTP/1.1 200 OK
HTTP/1.1 200 OK
HTTP/1.1 404 Not Found
```