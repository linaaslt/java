
	/**
	*	// s = v * t
	*
	*	v = v0 - ( a * t ^ 2 ) / 2
	*
	*	// s = ( v0 -  ( a * t ^ 2 ) / 2 ) * t
	*
	*	h = ( v0 -  ( g * t ^ 2 ) / 2 ) * t
	*
	*	Ivedam:
	*	v0 - pradinis raketos greitis
	*	dt - laiko �ingsnis
	*	skaiciuojam ir i�vedam t, v, h
	*	kai h ima ma�eti dt ma�inam 10 kartu, bet gr�tam vienu �ingsniu atgal
	*	kai auk�tis suma�eja 3%, dt gra�inam koks buvo ivestas iki kol nukris
	*
	*/