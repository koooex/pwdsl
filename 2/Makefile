CC = gcc
LIB = -lpython2.7
INC = -I/usr/include/python2.7/
CFLAGS = -g -Wall -pthread

SRC := ${shell ls *.c}
OBJ := $(patsubst %.c,%.o,${SRC})

$(warning ${SRC})
$(warning ${OBJ})

TARGET = engine
SHARES = base.so

.PHONY: all
all: ${TARGET} ${SHARES}

$(filter %, ${TARGET}): %: %.c
	${CC} ${CFLAGS} ${INC} ${LIB} -o $@ $<

$(filter %.so, ${SHARES}): %.so: %.c
	${CC} ${CFLAGS} -shared -o $@ $<

.PHONY: clean
clean:
	@-rm -rf *.dSYM *.so ${TARGET}


