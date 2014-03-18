echo "run-forever.sh:: Start running the app..."

nohup java -jar ../target/kaizen-0.0.1-Yelp.jar server config/dev_config.yml 0<&- &> /tmp/app.log &

# Grep the pid of Java process and save the PID in a temp file
# Specifically look for dropwizard jar `ps -elf | awk '/kaizen.*.jar/ {print $4}' | head -1`
pid=`ps -elf | awk '/java -jar/ {print $4}' | head -1`
echo $pid > /tmp/app.pid

echo "run-forever.sh:: Successfully started the app (pid:$pid)"

java -jar target/kaizen-0.0.1-Yelp.jar server config/dev_config.yml
