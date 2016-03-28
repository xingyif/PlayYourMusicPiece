----------------------
    INTRODUCTION
----------------------
This Music Editor project is a collaboration between Alex Fleetwood and Yifan Xing.

The Music Editor follows the MVC design pattern,
	and is composed of a model, a view, and a controller. 
It takes a musical composition as an input through the controller,
	maintains all data of the composition in the model,
	and displays the song to the user through the view.

-----------------------------------
    HW #5: THE ORIGINAL MODEL
-----------------------------------

The original model created by Yifan Xing has the following behavior:
- add a note to the composition
- remove a note from the composition
- move a note from one pitch to another
- edit the length of a note
- get the highest pitch in the composition
- get the lowest pitch in the composition
- combine two compositions such that they play simultaneously
- combine two compositions such that they play consecutively
- print the composition as text to the console


-----------------------------------
    HW #6: CHANGES TO THE MODEL
-----------------------------------

The new version of the model, modified by Alex Fleetwood and Yifan Xing,
	made changes to the behavior of the original model:
ADDED: 
- remove all notes from the composition
- get a list of all notes in the composition
- compute the duration of the composition
- get the current beat number
- set the current beat number
- get the tempo of the composition
- set the tempo of the composition
REMOVED: 
- print the composition as text to the console (moved to the View)

-----------------------------
    HW #6: IMPLEMENTATION
-----------------------------

THE CS3500.MUSIC PACKAGE
------------------------
- Class: MusicEditor

THE CS3500.MUSIC.MODEL PACKAGE
------------------------------
- Interface: MusicModel

	This represents a model with the behavior described above.
- Class: MusicModelImpl

	This class implements the MusicModel interface.
	This class has a nested class, Builder, which implements the CompositionBuilder interface.
	The Builder class permits creating new MusicModelImpl without calling the constructor directly.
	A MusicModelImpl has:
		- a map of integer values to a list of notes
		- the value of the lowest pitch
		- the value of the highest pitch
		- the value of the current beat
		- the value of the tempo
	All fields are private so that the user cannot directly modify them,
		but some fields be modified through provided methods.
	All the music notes are stored in the TreeMap. 
	Each key in the map corresponds to the start beat of a note in the composition.
	If two notes have the same start beat, they will be stored within the map as an ArrayList of notes.


- Class: Note

	This class represents a note. 
	A Note has the following fields:
		- a start beat
		- an end beat
		- a pitch value
		- an instrument
		- a volume
	A Note has the following behavior:
		- edit the start beat and end beat of the note
		- get information about a field
	The fields are private so that the user cannot directly modify the values, 
	but there are methods that allow the user to receive information about the note.


- Class: Pitch

	This class represents a pitch.
	The class is enumerated to contain only the twelve tones of the Western musical scale
	in order of their appearance in an octave:
		- C, C#, D, D#, E, F, F#, G, G#, A, A#, B
	A Pitch has the following fields:
		- a name for itself, represented as a String
		- (implicit by enum) an integer ordinal representing its order in the octave
	The name field is private so that a user cannot modify the name of a Pitch.
	The Pitch class has the behavior:
		- get the ordinal
		- get the String representation of the name
		- given an int, return a Pitch object with the same ordinal

THE CS3500.MUSIC.UTIL PACKAGE
-----------------------------
- Interface: CompositionBuilder
	This represents a builder which promises the following behavior:
		- build a model of the composition
		- set the tempo of the model
		- add a note to the model
- Class: MusicReader
	This class follows the factory pattern,
	and produces a model given a source of music and a CompositionBuilder.

THE CS3500.MUSIC.VIEW PACKAGE
-----------------------------
- Interface: MusicView
	This represents a view that either visually draws or audibly plays the music composition.
	It promises the following behavior:
		- display the view to the user
- Interface: MidiView
	This extends the representation of MusicView to specifically work with Midi systems.
	Satisfies the type requirement of the skeleton code.
- Class: ConsoleView
	This class represents a view in which the composition is printed to the console
	as text.
	This class implements the MusicView interface.
	A ConsoleView has the following fields:
		- a MusicModel of Note objects
	A ConsoleView has the following behavior, not given in the interface:
		- print a single pitch, centered within 5 columns of characters
		- print the range of all pitches in the composition
		- print the pitches and all beats with notes in the composition
- Class: GuiViewFrame
	This class represents a view in which the composition is displayed on a GUI.
	This class extends the javax.swing.JFrame class, 
		and implements the MusicView interface.
	A GuiViewFrame has the following fields:
		- a JPanel, which knows how to draw elements of the model
		- a MusicModel of Note objects
	A GuiViewFrame has the following behavior, not given in the interface:
		- get the preferred window dimensions of the frame
		- get a Graphics object of itself
- Class: ConcreteGuiViewPanel
	This class is a helper for the GuiViewFrame class.
	It extends JPanel and overrides the paintComponent method.
	A ConcreteGuiViewPanel has the following fields:
		- a MusicModel, provided by GuiViewFrame
	A ConcreteGuiViewPanel has the following helper methods for paintComponent:
		- print the beat number for the beginning of every measure
		- print the pitches from highest to lowest value descending
		- print all notes as filled rectangles on the staff
		- print the staff as a grid of rectangles
		- print the cursor for the current beat
- Class: MidiViewImpl
	This class represents a view in which the composition is rendered in Midi messages
	so it can be heard.
	It implements the MidiView (MusicView) interface.
	A MidiViewImpl has the following fields:
		- a MusicModel of Note objects
		- a Synthesizer object
		- a Receiver object
	A MidiViewImpl has the following behavior, in addition to that of MusicView:
		- play a Note object by sending a MidiMessage to the receiver
- Class: MockMidiDevice
	This class emulates a Synthesizer object, and implements the Synthesizer interface,
	but its only purpose is to create a log that stores MidiMessages sent to a Receiver.
	It has two relevant methods:
		- get the log
		- get a new Receiver object
- Class: MockReceiver
	This class emulates a Receiver object, and implements the Receiver interface,
	but its only purpose is to receive and record MidiMessages in a StringBuilder log.
	It has one relevant method:
		- append received messages to the end of the log
	