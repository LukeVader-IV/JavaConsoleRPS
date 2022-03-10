TARGET = RockPaperScisors
SOURCE = main.c
SOURCES = $(wildcard src/*.c)
OBJECTS = $(patsubst src/%.c, objects/%.o, $(SOURCES))

all: $(TARGET)

$(TARGET): $(SOURCE) $(OBJECTS)
	gcc $^ -o $@

objects/%.o: src/%.c
	mkdir -p objects
	gcc -c $^ -o $@

install:
	echo "installing is not supported"

clean:
	rm -rf objects/* $(TARGET)

run: $(TARGET)
	./$(TARGET)
