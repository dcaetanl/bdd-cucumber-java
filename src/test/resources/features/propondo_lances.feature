#language: pt

Funcionalidade: Propondo lances ao leilao

Cenario: Propondo um unico lance valido
	 Dado um lance valido
	 Quando propoe um leilao
	 Entao o lance e aceito
	 
Cenario: Propondo varios lances validos
	 Dado um lance de R$ 10.00 do usuario "fulano"
	 E um lance de R$ 20.00 do usuario "beltrano"
	 Quando propoe varios lances ao leilao
	 Entao os lances sao aceitos

Esquema do Cenario: Propondo um lance invalido
	 Dado um lance invalido de R$ <valor> do usuario '<usuario>'
	 Quando propoe um leilao
	 Entao o lance nao e aceito	 
	 
Exemplos:
	| valor | usuario  |		
	|  -10  | fulano	 |
	|    0 	| beltrano |

Cenario: Propondo uma sequencia de lances
	 Dado dois lances
	 		| valor | usuario  |		
	 		|  10  	| beltrano |
	 		|  20		| beltrano |
	 Quando propoe varios lances ao leilao
	 Entao o segundo lance nao e aceito