#include "moves.h"

/* returns: 0 - loss   1 - draw    2 - victory  5 - ERROR*/
/* input:   0 - rock   1 - paper   2 - scisors */

int checkwin (int player, int opponent){
	switch (player) {
		case 0:
			switch (opponent){
				case 0:
					return 1;
				case 1:
					return 0;
				case 2:
					return 2;
				default:
					/* ERROR: WRONG INPUT!!!!! */
					return 5;
			}
		case 1:
			switch (opponent){
				case 0:
					return 2;
				case 1:
					return 1;
				case 2:
					return 0;
				default:
					/* ERROR: WRONG INPUT!!!!! */
					return 5;
			}
		case 2:
			switch (opponent){
				case 0:
					return 0;
				case 1:
					return 2;
				case 2:
					return 1;
				default:
					/* ERROR: WRONG INPUT!!!!! */
					return 5;
			}
		default:
			/* ERROR: WRONG INPUT!!!!! */
			return 5;
		}
}
