## SegaBank
#### Louis GAUTIER - Bryan BRETON

Nouvel établissement financier qui permet à des particuliers de placer leurs deniers personnels et bénéficier d’avantages incroyables.
Vous devez développer la première version de leur application « console » en Java avec JPA pour l’accès aux données.
Vous devez donc créer une application qui permet de manipuler différents types de comptes bancaires : les comptes simples, les comptes épargnes et les comptes payants.

Tous les types de comptes sont caractérisés par :
- Un identifiant et un solde ;
- Un compte peut subir les opérations de versement et de retrait.
- Pour ces deux opérations, il faut connaître le montant de l'opération ;
- Pour consulter un compte on peut faire appel à sa méthode « toString() ».

Un compte simple est un compte qui possède un découvert. Ce qui signifie que ce compte peut être débiteur jusqu'à la valeur du découvert.
Un compte Epargne est un compte bancaire qui possède en plus un champ « tauxInteret » et une méthode « calculIntérêt() » qui permet de mettre à jour le solde en tenant compte des intérêts.
Un Compte payant est un compte bancaire pour lequel chaque opération de retrait et de versement est payante et vaut 5 % du montant de l'opération.
Chaque compte est administré par une et une seule agence. Les agences sont définies par id, un code et une adresse.
