# RPG Java - Projeto POO

## Descrição

Este é um RPG de terminal desenvolvido em Java utilizando Programação Orientada a Objetos (POO).

O projeto foi criado com o objetivo de praticar conceitos de orientação a objetos, herança, polimorfismo, encapsulamento e organização em múltiplas classes e pacotes.

O jogo possui combate por turnos, sistema de classes, inimigos com inteligência artificial, progressão por experiência, inventário, itens e uma classe secreta desbloqueável.

---

## Funcionalidades Implementadas

### Sistema de Personagens

Classes disponíveis:

* Guerreiro
* Mago
* Arqueiro
* Paladino
* Assassino
* Berserker
* Curandeiro

Classe secreta:

* Deus (desbloqueada após derrotar o Boss Final)

---

### Sistema de Combate

* Combate por turnos
* Ataques básicos
* Habilidades especiais
* Sistema de mana
* Ganho de mana ao causar dano
* Cura através de habilidades e poções

---

### Sistema de Mana

Todos os personagens ganham:

* +10 mana ao causar dano

Classe Deus:

* +15 mana ao causar dano

---

### Sistema de Inimigos

Inimigos atuais:

* Goblin
* Orc
* Esqueleto
* Mago Sombrio
* Dragão Ancestral (Boss Final)

Cada inimigo possui comportamento próprio através de IA simples.

---

### Sistema de IA

Os inimigos tomam decisões automaticamente.

Exemplos:

* Goblin: agressivo
* Orc: tenta finalizar o jogador
* Mago Sombrio: prioriza magia
* Dragão Ancestral: possui múltiplas fases de combate

---

### Sistema de Progressão

* XP por inimigo derrotado
* Ouro por inimigo derrotado
* Sistema de níveis
* Aumento de atributos ao subir de nível

---

## Estrutura do Projeto

src/

├── personagens/

├── inimigos/

├── itens/

├── sistema/

└── Main.java

---

## Classes Jogáveis

### Guerreiro

* Vida: 120
* Dano: 15
* Habilidade: Golpe Devastador

### Mago

* Vida: 90
* Dano: 12
* Habilidade: Bola de Fogo

### Arqueiro

* Vida: 100
* Dano: 14
* Habilidade: Chuva de Flechas

### Paladino

* Vida: 130
* Dano: 12
* Habilidade: Luz Sagrada

### Assassino

* Vida: 80
* Dano: 18
* Passiva: Crítico
* Habilidade: Ataque Sombrio

### Berserker

* Vida: 110
* Dano: 16
* Passiva: Fúria
* Habilidade: Fúria Selvagem

### Curandeiro

* Vida: 95
* Dano: 10
* Habilidade: Grande Cura

### Deus

* Vida: 150
* Dano: 20

Habilidades:

* Julgamento Divino
* Milagre
* Apocalipse

---

## Boss Final

### Dragão Ancestral

Recompensa:

* 500 XP
* 300 Ouro

Fases:

### Fase 1

* Ataques normais

### Fase 2

* Uso frequente da Chama Infernal

### Fase 3

* Modo Fúria

Ao derrotar o Dragão Ancestral, a classe Deus é desbloqueada.

---

## Funcionalidades Planejadas

* Inventário completo
* Loja
* Equipamentos
* Sistema de drops
* Status negativos

  * Veneno
  * Sangramento
  * Queimadura
* Sistema de salvamento
* Múltiplas áreas
* Chefes secundários
* Conquistas
* Sistema de raridade de itens

---

## Tecnologias Utilizadas

* Java
* Programação Orientada a Objetos (POO)
* VS Code

---

## Autor

Projeto criado por João Victor Fortes do Nascimento.
