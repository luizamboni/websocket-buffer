Instalar
===
$ \. mvncompile.sh 

Rodar todos os testes unit�rios
===
$ \. testall.sh 



Desafio Buffer
===

Teremos 3 aplicações: Produtor, Consumidor e Buffer. 

Cada uma delas rodando em m�quinas distintas. 

#Buffer:

	- Buffer irá armazenar valores inteiros 
 	- a quantidade de valores que ela irá guardar dependerá do que for passado por parâmetro na sua inicialização. 
	- Quando inicializada ela receberá o nº de porta que ficará ouvindo e a quantidade de números que irá armazenar. 
	- Quando um valor é adicionado ou retirado de Buffer, ele deve mostrar uma mensagem na tela do tipo: "Valor x adicionado (ou retirado) em Buffer pelo Produtor y (ou Consumidor y). 
	- Se um Produtor tenta colocar um valor no Buffer e ele esta cheio, ele mostra em sua tela "Produtor y tentou colocar item no Buffer cheio" e libera o Buffer. 
	- Se um Consumidor tenta retirar um valor do Buffer e ele esta vazio, ele mostra em sua tela "Consumidor y tentou retirar item do Buffer vazio" e libera o Buffer. 

#Produtor:

	- Produtor coloca números inteiros aleat�rios em Buffer. 
	- Um dos par�metros que ele recebe como inicialização define quantas Threads, ou seja, instâncias de Produtor serão 	

inicializados. 
	- Cada Produtor (ou seja, cada Thread) será denominado Produtor1, Produtor2, etc... 
	- A partir do momento que um Produtor tenta colocar um valor em Buffer, ele começa a contar o tempo. E quando ele consegue, ele mostra na tela, o valor que ele colocou e quanto tempo levou até conseguir colocar o valor em Buffer, com a mensagem  "Colocado o valor x no  Buffer pelo Produtor y", onde x é o valor e y o nº do Produtor. 
	


#Consumidor:

	- Consumidor retira os valores do Buffer. Um dos parâmetros que ele recebe como inicialização define quantas Threads, ou seja, quantas instâncias de Consumidor serão inicializadas. 
	- Cada Consumidor (ou seja, cada Thread) será denominado Consumidor1, Consumidor2, etc... 
	- A partir do momento que um Consumidor tenta retirar um valor do Buffer, ele começa a contar o tempo. 
	E quando ele consegue, ele mostra na tela, o valor que ele retirou e quanto tempo levou até conseguir retirar o valor do Buffer, com a mensagem 
	"Retirado o valor x do Buffer pelo Consumidor y", onde x é o valor e y o não do Consumidor. 
	
	
