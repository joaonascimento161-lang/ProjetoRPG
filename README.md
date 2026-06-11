# 🛡️ Projeto RPG em Java

Um RPG desenvolvido em Java utilizando Programação Orientada a Objetos (POO) e Java Swing, contendo sistema de combate, habilidades, inventário, equipamentos, loja, progressão de níveis e salvamento de progresso.

---

# 📖 Sobre o Projeto

Este projeto foi desenvolvido com o objetivo de praticar conceitos fundamentais e intermediários de Programação Orientada a Objetos em Java.

O jogador escolhe uma classe, enfrenta inimigos, evolui seu personagem, coleta itens, compra equipamentos e pode salvar/carregar seu progresso.

---

# ⚔️ Funcionalidades

## Classes Jogáveis

O jogo possui as seguintes classes:

* Guerreiro
* Mago
* Arqueiro
* Paladino
* Assassino
* Berserker
* Curandeiro
* Deus (classe secreta)

Cada classe possui atributos próprios e uma habilidade especial.

---

## Sistema de Combate

Durante o combate o jogador pode:

* Atacar
* Utilizar habilidade especial
* Utilizar itens do inventário

O sistema conta com:

* Barra de Vida (HP)
* Barra de Mana (MP)
* Histórico das ações do combate
* Sistema de vitória e derrota

---

## Sistema de Experiência (XP)

Ao derrotar inimigos o jogador recebe:

* XP
* Ouro

Ao subir de nível:

* A Vida Máxima aumenta
* O Dano aumenta

---

## Sistema de Inventário

O inventário permite:

* Armazenar itens
* Utilizar consumíveis
* Equipar equipamentos
* Remover itens utilizados

Itens disponíveis:

### Consumíveis

* Poção de Vida
* Poção de Mana

### Equipamentos

* Armas
* Armaduras

---

## Sistema de Equipamentos

### Armas

Aumentam o dano do personagem.

Exemplo:

* Espada de Ferro (+5 dano)

### Armaduras

Aumentam a vida máxima.

Exemplo:

* Armadura de Couro (+20 vida máxima)

---

## Sistema de Loja

A loja permite comprar:

### Consumíveis

* Poção de Vida (30 ouro)
* Poção de Mana (25 ouro)

### Equipamentos

* Espada de Ferro (100 ouro)
* Armadura de Couro (150 ouro)

As compras são realizadas utilizando ouro obtido em combate.

---

## Sistema de Drops

Ao derrotar inimigos existe a possibilidade de obter:

* Poções
* Equipamentos
* Outros itens futuramente

---

## Sistema de Save e Load

O progresso do jogador é armazenado em arquivo de texto.

Informações salvas:

* Classe
* Nível
* XP
* Vida Atual
* Mana Atual
* Ouro
* Inventário
* Arma Equipada
* Armadura Equipada
* Estado de desbloqueio da classe Deus

Ao carregar o jogo, todas essas informações são restauradas automaticamente.

---

# 📂 Estrutura do Projeto

```text
src
│
├── areas
│
├── inimigos
│
├── interfaces
│   ├── TelaPrincipal
│   ├── TelaCombate
│   ├── TelaInventario
│   └── TelaLoja
│
├── itens
│   ├── Item
│   ├── PocaoVida
│   ├── PocaoMana
│   ├── Equipamento
│   ├── Arma
│   ├── Armadura
│   ├── ItemFactory
│   └── EquipamentoFactory
│
├── main
│
├── personagens
│   ├── Personagem
│   ├── Guerreiro
│   ├── Mago
│   ├── Arqueiro
│   ├── Paladino
│   ├── Assassino
│   ├── Berserker
│   ├── Curandeiro
│   └── Deus
│
├── save
│   └── SaveManager
│
└── sistema
    └── XPSystem
```

---

# 🖥️ Interface Gráfica

O projeto utiliza Java Swing para criação das telas.

Telas implementadas:

* Tela Principal
* Tela de Combate
* Tela de Inventário
* Tela da Loja

---

# 🛠️ Tecnologias Utilizadas

* Java
* Java Swing
* Programação Orientada a Objetos (POO)
* Manipulação de Arquivos (Save/Load)
* ArrayList
* Organização em Pacotes

---

# 📚 Conceitos Aplicados

* Encapsulamento
* Herança
* Polimorfismo
* Classes Abstratas
* Sobrescrita de Métodos
* Manipulação de Arquivos
* Interfaces Gráficas
* Estruturas de Dados

---

# 🚀 Como Executar

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/projeto-rpg.git
```

2. Abra o projeto em sua IDE Java.

3. Execute a classe:

```text
Main.java
```

---

# 🔮 Melhorias Futuras

* Novos inimigos
* Novas áreas
* Sistema de missões
* Boss Final
* Equipamentos raros
* Loja avançada
* Sistema de conquistas
* Melhorias visuais
* Efeitos sonoros

---

# 👨‍💻 Autor

João Victor Fortes do Nascimento

Projeto desenvolvido para praticar Programação Orientada a Objetos em Java através da criação de um RPG completo com interface gráfica.
