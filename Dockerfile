from registry.adcade.com/library/java:oracle-java-7

COPY target/spray-tiny-url.jar /var/lib/spray-tiny-url/spray-tiny-url.jar
COPY docker-entrypoint.sh /entrypoint.sh

EXPOSE 8080

USER adcade

ENTRYPOINT ["/entrypoint.sh"]
