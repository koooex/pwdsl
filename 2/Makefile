CC = gcc
CFLAGS = -g -Wall

SRC := ${shell ls *.c}
OBJ := $(patsubst %.c,%.o,${SRC})

$(warning ${SRC})
$(warning ${OBJ})

TARGET = engine base.so

.PHONY: all
all: ${TARGET} 

engine: engine.c
	${CC} ${CFLAGS} -o $@ $<

$(filter %.so, ${TARGET}): %.so: %.c
	${CC} ${CFLAGS} -shared -o $@ $<

.PHONY: clean
clean:
	@-rm -rf *.dSYM *.so engine


