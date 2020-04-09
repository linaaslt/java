
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
	*	dt - laiko žingsnis
	*	skaiciuojam ir išvedam t, v, h
	*	kai h ima mažeti dt mažinam 10 kartu, bet gržtam vienu žingsniu atgal
	*	kai aukštis sumažeja 3%, dt gražinam koks buvo ivestas iki kol nukris
	*
	*/