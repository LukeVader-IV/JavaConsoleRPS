#include <stdio.h>
#include <stdlib.h>

#include <sys/types.h>
#include <sys/socket.h>

#include <netinet/in.h>
#include <unistd.h>

#define PORT 6969

int client() {

	char mesage[256] = "sample response";

	int netsock;
	netsock = socket(AF_INET, SOCK_STREAM, 0);

	struct sockaddr_in server_address;
	server_address.sin_family = AF_INET;
	server_address.sin_port = htons(PORT);
	server_address.sin_addr.s_addr = INADDR_ANY;

	int connection_status = connect(netsock, (struct sockaddr *) &server_address, sizeof(server_address));

	if (connection_status == 1) {
		printf("connection failed\n");
	}

	char server_resp[256];
	recv(netsock, server_resp, sizeof(server_resp), 0);

	printf("the server sent: %s\n", server_resp);
	close(netsock);
	return 0;
}

int server(int move) {
	char message[256] = "sample message";

	int netsock;
	netsock = socket(AF_INET, SOCK_STREAM, 0);

	struct sockaddr_in server_address;
	server_address.sin_family = AF_INET;
	server_address.sin_port = htons(PORT);
	server_address.sin_addr.s_addr = INADDR_ANY;

	bind(netsock, (struct sockaddr*) &server_address, sizeof(server_address));

	listen(netsock, 5);

	int client_socket;
	client_socket = accept(netsock, NULL, NULL);

	send(client_socket, message, sizeof(message), 0);

	close(netsock);

	return 0;
}

int initiate() {

}
