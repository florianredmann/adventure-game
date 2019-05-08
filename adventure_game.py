orig_src = [x[:-1] for x in open('./old_source/adventure-game/Game.java').readlines()]


short_vars = {}
long_vars = {}

g_t, e_t, c_i_t, c_t = [None] * 4


def conv_umlauts(t):
    k = [y(x) for x in 'oe ue ae ss'.split(' ') for y in [str.upper, str.lower]]
    u = [y(x) for x in 'ö ü ä ß'.split(' ') for y in [str.upper, str.lower]]
    for kc, uml in zip(k, u):
        t = t.replace(kc, uml)
    return t


instructions = dict(zip(
    'GO_TEXT, EQUIPMENT_TEXT, CHOICE_INTRO_TEXT, CHOICE_TEXT'.split(', '),
    [g_t, e_t, c_i_t, c_t],
))


def prep_str(t):
    return conv_umlauts(t.strip().replace('\\n', '\n'))[1:-2]


# print(instructions)

for l in orig_src:
    if '=' in l:
        a, b = l.split('=')
        try:
            v, vname = a.strip().split(' ')[:4], a.strip().split(' ')[4]
            if v == 'private static final String'.split(' '):
                if vname.count('_') == 2 and len(vname) == 5:
                    # print(vname)
                    long_vars[vname] = prep_str(b)

                elif vname == 'GO_TEXT':
                    g_t = prep_str(b)
                elif vname == 'EQUIPMENT_TEXT':
                    e_t = prep_str(b)
                elif vname == 'CHOICE_INTRO_TEXT':
                    c_i_t = prep_str(b)
                elif vname == 'CHOICE_TEXT':
                    c_t = prep_str(b)
                else:
                    short_vars[vname] = prep_str(b)
                    # print('other var:', vname)
        except Exception as e:
            pass # print(e, a)

#print('LV:')
# for p in long_vars.items():
#    print(p)

print(g_t, e_t, sep='\n')
equipment = input()
print(c_i_t)
print(c_t)
choice = input()


def find_res(choice):
    res = dict(zip(
        [x for x in 'weiter, lager, umweg, baum, nachgehen, plündern, ausweichen, weg, pfeil, anschleichen, '
                    'osten, westen, zelt, attackieren, brandpfeil, verstecken'.split(', ')],
        'A_1_Y, A_1_O, B_1_P, B_1_G, B_1_Y, A_2_O, A_2_P, B_2_G, B_2_Y, '
        'B_2_O, A_3_P, A_3_G, A_4_Y, A_4_O, B_4_P, B_4_G'.split(', ')
    ))[choice.lower()]
    return long_vars[res]


def evaluate(eq, ch):
    d = None
    if eq == 'Schwert':
        d = dict(zip(
            'Wald, Berg, Lager, abseits'.split(', '),
            list(short_vars.values())[:4],
        ))

    else:
        d = dict(zip(
            'Wald, Berg, Lager, abseits'.split(', '),
            list(short_vars.values())[4:],
        ))

    print(d[choice])
    return input()

print(find_res(evaluate(equipment, choice)))
