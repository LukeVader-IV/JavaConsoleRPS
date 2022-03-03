all: RockPaperScisors

RockPaperScisors: ./build/main.o ./build/moves.o ./build/network.o
	gcc -o RockPaperScisors ./build/main.o ./build/moves.o ./build/network.o

./build/main.o: ./src/main.c
	mkdir -p build
	gcc -c ./src/main.c -o ./build/main.o

./build/moves.o: ./src/moves.c
	mkdir -p build
	gcc -c ./src/moves.c -o ./build/moves.o

./build/network.o: ./src/network.c
	mkdir -p build
	gcc -c ./src/network.c -o ./build/network.o

install:
	echo "installing is not supported"

run: RockPaperScisors
	./RockPaperScisors
