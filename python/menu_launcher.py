import curses
import json
import requests
screen = curses.initscr()
curses.noecho()
curses.cbreak()
curses.start_color()
curses.init_pair(1,curses.COLOR_BLACK, curses.COLOR_WHITE) # Sets up color pair #1, it does black text with white background
screen.keypad(1)

first_menu = { "id": 1, "description": "Start menu", "url": "http://localhost:8080/grapias" }

def runmenu(oldmenu, menu):
    pos=0
    optioncount = len(menu)

    oldpos=None # used to prevent the screen being redrawn every time
    x = None #control for while loop, let's you scroll through options until return key is pressed then returns pos to program

    while True:
        if x==ord('\n'):
          pos=0
          screen.clear()
          oldmenu = menu[oldpos]
          menu = call_rest_enter(menu[oldpos])
          oldpos=None 
          optioncount = len(menu)

        if x==ord('b'):
          pos=0
          oldpos=None 
          screen.clear()
          menu = call_rest_back(oldmenu)
          optioncount = len(menu)

        if pos != oldpos:
          oldpos = pos
          screen.border(0)

          # Display all the menu items, showing the 'pos' item highlighted
          for index in range(optioncount):
            textstyle = curses.A_NORMAL
            if pos==index:
              textstyle = curses.color_pair(1)
            screen.addstr(5+index,4, menu[index]['description'], textstyle)
          screen.refresh()

        x = screen.getch() # Gets user input
        # What is user input?
        if x >= ord('1') and x <= ord(str(optioncount+1)):
          pos = x - ord('0') - 1 # convert keypress back to a number, then subtract 1 to get index
        elif x == 258: # down arrow
          if pos < optioncount - 1:
            pos += 1
          else: pos = 0
        elif x == 259: # up arrow
          if pos > 0:
            pos += -1
        else: pos = optioncount - 1
    return

def call_rest_enter(menu):
    menu_json = json.dumps(menu)
    headers = {'Content-type': 'application/json' }
    response = requests.post(menu['url'] + "/enter", data=menu_json, headers=headers)
    from StringIO import StringIO
    io = StringIO(response.content)

    return json.load(io)['menuItems']

def call_rest_back(menu):
    menu_json = json.dumps(menu)
    headers = {'Content-type': 'application/json' }
    response = requests.post(menu['url'] + "/back", data=menu_json, headers=headers)
    from StringIO import StringIO
    io = StringIO(response.content)

    return json.load(io)['menuItems']

menu=runmenu(first_menu, call_rest_enter(first_menu))
curses.endwin()