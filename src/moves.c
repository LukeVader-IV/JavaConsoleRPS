#include "moves.h"

/* returns: 0 - loss   1 - draw    2 - victory   3 - exiting   5 - ERROR*/
/* input:   0 - rock   1 - paper   2 - scisors */

/*				Win-Loss						substraction table	*/

/*		 		|	R	|	P	|	S						|	0	|	1	|	2			 */
/*		 -------------- 				--------------		 */
/*		 R	|	D	|	L	|	W 				0	|	0	|-1	|-2 			*/
/*		 P	|	W	|	D	| L 				1	|	1	|	0	|-1 			*/
/*		 S	|	L	|	W	|	L 				2	|	2	|	1	|	0 			*/

int checkwin (int player, int opponent){

	if (player == 3 || opponent == 3)
		return 3;
	else if (player > 2 || opponent > 2)
		return 5;

	switch (player - opponent)
		{
		case 0:
			return 1;
		case 1:
		case -2:
			return 2;

		case 2:
		case -1:
			return 0;

		default:
			return 5;
		}
}
