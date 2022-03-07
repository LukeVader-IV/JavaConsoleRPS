#include "moves.h"

/* returns: 0 - loss   1 - draw    2 - victory   3 - exiting   5 - ERROR*/
/* input:   0 - rock   1 - paper   2 - scisors */

/*        Win-Loss            Resulting Table      */

/*      | R | P | S             | 0 | 1 | 2        */
/*    --------------          --------------       */
/*    R | D | L | W           0 | 1 | 0 | 2       */
/*    P | W | D | L           1 | 2 | 1 | 0        */
/*    S | L | W | L           2 | 0 | 2 | 1        */

int checkwin (int player, int opponent){

	if (player == 3 || opponent == 3)
		return 3;
	else if (player > 2 || opponent > 2)
		return 5;

	int results[5] = {0, 2, 1, 0, 2};
	return results[player + 2 - opponent];
}
