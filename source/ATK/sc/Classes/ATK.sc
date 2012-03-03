/*
	Copyright the ATK Community, Joseph Anderson, and Josh Parmenter, 2011
		J Anderson	j.anderson[at]ambisonictoolkit.net 
		J Parmenter	j.parmenter[at]ambisonictoolkit.net 
	
	
	This file is part of SuperCollider3 version of the Ambisonic Toolkit (ATK).
	
	The SuperCollider3 version of the Ambisonic Toolkit (ATK) is free software:
	you can redistribute it and/or modify it under the terms of the GNU General
	Public License as published by the Free Software Foundation, either version 3
	of the License, or (at your option) any later version.
	
	The SuperCollider3 version of the Ambisonic Toolkit (ATK) is distributed in
	the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
	implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See
	the GNU General Public License for more details.
	
	You should have received a copy of the GNU General Public License along with the
	SuperCollider3 version of the Ambisonic Toolkit (ATK). If not, see
	<http://www.gnu.org/licenses/>.
*/


//---------------------------------------------------------------------
//	The Ambisonic Toolkit (ATK) is a soundfield kernel support library.
//
// 	Class (UGen superclass): Foa
//
// 	Class (UGen): FoaPanB
//
// 	Class (UGen): FoaDirectO
// 	Class (UGen): FoaDirectX
// 	Class (UGen): FoaDirectZ
// 	Class (UGen): FoaRotate
// 	Class (UGen): FoaTilt
// 	Class (UGen): FoaTumble
// 	Class (UGen): FoaFocusX
// 	Class (UGen): FoaFocusY
// 	Class (UGen): FoaFocusZ
// 	Class (UGen): FoaPushX
// 	Class (UGen): FoaPushY
// 	Class (UGen): FoaPushZ
// 	Class (UGen): FoaPressX
// 	Class (UGen): FoaPressY
// 	Class (UGen): FoaPressZ
// 	Class (UGen): FoaZoomX
// 	Class (UGen): FoaZoomY
// 	Class (UGen): FoaZoomZ
// 	Class (UGen): FoaDominateX
// 	Class (UGen): FoaDominateY
// 	Class (UGen): FoaDominateZ
// 	Class (UGen): FoaAsymmetry
//
// 	Class: FoaRTT
// 	Class: FoaMirror
// 	Class: FoaDirect
// 	Class: FoaDominate
// 	Class: FoaZoom
// 	Class: FoaFocus
// 	Class: FoaPush
// 	Class: FoaPress
//
// 	Class (UGen): FoaProximity
// 	Class (UGen): FoaNFC
// 	Class (UGen): FoaPsychoShelf
//
// 	Class: AtkMatrixMix
// 	Class: AtkKernelConv
//
// 	Class: FoaDecode
// 	Class: FoaEncode
//
// 	Class: FoaXform
// 	Class: FoaTransform
//
//	The Ambisonic Toolkit (ATK) is intended to bring together a number of tools and
//	methods for working with Ambisonic surround sound. The intention is for the toolset
//	to be both ergonomic and comprehensive, providing both classic and novel algorithms
//	to creatively manipulate and synthesise complex Ambisonic soundfields.
//	
//	The tools are framed for the user to think in terms of the soundfield kernel. By
//	this, it is meant the ATK addresses the holistic problem of creatively controlling a
//	complete soundfield, allowing and encouraging the composer to think beyond the placement
//	of sounds in a sound-space and instead attend to the impression and image of a soundfield.
//	This approach takes advantage of the model the Ambisonic technology presents, and is
//	viewed to be the idiomatic mode for working with the Ambisonic technique.
//	
//	
//	We hope you enjoy the ATK!
//	
//	For more information visit http://ambisonictoolkit.net/ or
//	email info[at]ambisonictoolkit.net
//
//---------------------------------------------------------------------


FoaPanB : Panner {
	
	*ar { arg in, azimuth=0, elevation=0, mul = 1, add = 0;
		^this.multiNew('audio', in, azimuth, elevation ).madd(mul, add);
	}
	
	init { arg ... theInputs;
		inputs = theInputs;		
		channels = [ OutputProxy(\audio,this,0), OutputProxy(\audio,this,1),
					OutputProxy(\audio,this,2), OutputProxy(\audio,this,3) ];
		^channels
	}
}


Foa : Panner {
		
	init { arg ... theInputs;
		inputs = theInputs;		
		channels = [ OutputProxy(\audio,this,0), OutputProxy(\audio,this,1),
					OutputProxy(\audio,this,2), OutputProxy(\audio,this,3) ];
		^channels
	}
	
 	checkInputs { ^this.checkNInputs(4) }
 	
 	}

	
FoaDirectO : Foa {
	*ar { arg w, x, y, z, angle = pi/2, mul = 1, add = 0;
		^this.multiNew('audio', w, x, y, z, angle).madd(mul, add);
	}
}


FoaDirectX : Foa {
	*ar { arg w, x, y, z, angle = pi/2, mul = 1, add = 0;
		^this.multiNew('audio', w, x, y, z, angle).madd(mul, add);
	}
}

FoaDirectY : FoaDirectX { }
FoaDirectZ : FoaDirectX { }

FoaRotate : Foa { 
	*ar { arg w, x, y, z, angle = 0, mul = 1, add = 0;
		^this.multiNew('audio', w, x, y, z, angle).madd(mul, add);
	}
} 
FoaTilt : FoaRotate { }
FoaTumble : FoaRotate { }

FoaFocusX : FoaRotate { }
FoaFocusY : FoaRotate { }
FoaFocusZ : FoaRotate { }

FoaPushX : FoaRotate { }
FoaPushY : FoaRotate { }
FoaPushZ : FoaRotate { }

FoaPressX : FoaRotate { }
FoaPressY : FoaRotate { }
FoaPressZ : FoaRotate { }

FoaZoomX : FoaRotate { }
FoaZoomY : FoaRotate { }
FoaZoomZ : FoaRotate { }

FoaBalance { 
	*ar { arg w, x, y, z, angle = 0, mul = 1, add = 0;
		^FoaZoomY.ar(w, x, y, z, angle, mul, add);
	}
} 


FoaDominateX : Foa {	
	*ar { arg w, x, y, z, gain = 0, mul = 1, add = 0;
		^this.multiNew('audio', w, x, y, z, gain).madd(mul, add);
	}	
}

FoaDominateY : FoaDominateX { }
FoaDominateZ : FoaDominateX { }

FoaAsymmetry : FoaRotate { }


FoaRTT { 
	*ar { arg w, x, y, z, rotAngle = 0, tilAngle = 0, tumAngle = 0, mul = 1, add = 0;

		#w, x, y, z = FoaRotate.ar(w, x, y, z, rotAngle);
		#w, x, y, z = FoaTilt.ar(w, x, y, z, tilAngle);
		^FoaTumble.ar(w, x, y, z, tumAngle, mul, add);
	}
} 

FoaMirror { 
	*ar { arg w, x, y, z, theta = 0, phi = 0, mul = 1, add = 0;

		#w, x, y, z = FoaRotate.ar(w, x, y, z, theta.neg);
		#w, x, y, z = FoaTumble.ar(w, x, y, z, phi.neg);
		#w, x, y, z = FoaXform.ar([w, x, y, z], FoaXformerMatrix.newMirrorX);
		#w, x, y, z = FoaTumble.ar(w, x, y, z, phi);
		^FoaRotate.ar(w, x, y, z, theta, mul, add);
	}
} 

FoaDirect { 
	*ar { arg w, x, y, z, angle = 0, theta = 0, phi = 0, mul = 1, add = 0;

		#w, x, y, z = FoaRotate.ar(w, x, y, z, theta.neg);
		#w, x, y, z = FoaTumble.ar(w, x, y, z, phi.neg);
		#w, x, y, z = FoaDirectX.ar(w, x, y, z, angle);
		#w, x, y, z = FoaTumble.ar(w, x, y, z, phi);
		^FoaRotate.ar(w, x, y, z, theta, mul, add);
	}
} 

FoaDominate { 
	*ar { arg w, x, y, z, gain = 0, theta = 0, phi = 0, mul = 1, add = 0;

		#w, x, y, z = FoaRotate.ar(w, x, y, z, theta.neg);
		#w, x, y, z = FoaTumble.ar(w, x, y, z, phi.neg);
		#w, x, y, z = FoaDominateX.ar(w, x, y, z, gain);
		#w, x, y, z = FoaTumble.ar(w, x, y, z, phi);
		^FoaRotate.ar(w, x, y, z, theta, mul, add);
	}
} 

FoaZoom { 
	*ar { arg w, x, y, z, angle = 0, theta = 0, phi = 0, mul = 1, add = 0;

		#w, x, y, z = FoaRotate.ar(w, x, y, z, theta.neg);
		#w, x, y, z = FoaTumble.ar(w, x, y, z, phi.neg);
		#w, x, y, z = FoaZoomX.ar(w, x, y, z, angle);
		#w, x, y, z = FoaTumble.ar(w, x, y, z, phi);
		^FoaRotate.ar(w, x, y, z, theta, mul, add);
	}
} 

FoaFocus { 
	*ar { arg w, x, y, z, angle = 0, theta = 0, phi = 0, mul = 1, add = 0;

		#w, x, y, z = FoaRotate.ar(w, x, y, z, theta.neg);
		#w, x, y, z = FoaTumble.ar(w, x, y, z, phi.neg);
		#w, x, y, z = FoaFocusX.ar(w, x, y, z, angle);
		#w, x, y, z = FoaTumble.ar(w, x, y, z, phi);
		^FoaRotate.ar(w, x, y, z, theta, mul, add);
	}
} 

FoaPush { 
	*ar { arg w, x, y, z, angle = 0, theta = 0, phi = 0, mul = 1, add = 0;

		#w, x, y, z = FoaRotate.ar(w, x, y, z, theta.neg);
		#w, x, y, z = FoaTumble.ar(w, x, y, z, phi.neg);
		#w, x, y, z = FoaPushX.ar(w, x, y, z, angle);
		#w, x, y, z = FoaTumble.ar(w, x, y, z, phi);
		^FoaRotate.ar(w, x, y, z, theta, mul, add);
	}
} 

FoaPress { 
	*ar { arg w, x, y, z, angle = 0, theta = 0, phi = 0, mul = 1, add = 0;

		#w, x, y, z = FoaRotate.ar(w, x, y, z, theta.neg);
		#w, x, y, z = FoaTumble.ar(w, x, y, z, phi.neg);
		#w, x, y, z = FoaPressX.ar(w, x, y, z, angle);
		#w, x, y, z = FoaTumble.ar(w, x, y, z, phi);
		^FoaRotate.ar(w, x, y, z, theta, mul, add);
	}
} 


//------------------------------------------------------------------------
// Filters

FoaProximity : Foa { 
	*ar { arg w, x, y, z, distance = 1, mul = 1, add = 0;
		^this.multiNew('audio', w, x, y, z, distance).madd(mul, add);
	}

}

FoaNFC : Foa { 
	*ar { arg w, x, y, z, distance = 1, mul = 1, add = 0;
		^this.multiNew('audio', w, x, y, z, distance).madd(mul, add);
	}
		
}

FoaPsychoShelf : Foa { 
	*ar { arg w, x, y, z, freq = 400, k0 = (3/2).sqrt, k1 = 3.sqrt/2, mul = 1, add = 0;
		^this.multiNew('audio', w, x, y, z, freq, k0, k1).madd(mul, add);
	}
		
}


//------------------------------------------------------------------------
// AtkMatrixMix & AtkKernelConv

AtkMatrixMix {
	*ar { arg in, matrix, mul = 1, add = 0;
		
		var out;

		// wrap input as array if needed, for mono inputs
		in.isArray.not.if({ in = [in] });
		
		out = Mix.fill( matrix.cols, { arg i; // fill input
			UGen.replaceZeroesWithSilence(
				matrix.flop.asArray.at(i) * in.at(i)
			)
		});

		^out.madd(mul, add)
	}
}

AtkKernelConv {
	*ar { arg in, kernel, mul = 1, add = 0;
		
		var out;

		// wrap input as array if needed, for mono inputs
		in.isArray.not.if({ in = [in] });
		
		out = Mix.ar(
			kernel.shape.at(0).collect({ arg i;
				kernel.shape.at(1).collect({ arg j;
					Convolution2.ar(
						in.at(i),
						kernel.at(i).at(j),
						framesize: kernel.at(i).at(j).numFrames
					)
				})
			})
		);

		^out.madd(mul, add)
	}
}


//------------------------------------------------------------------------
// Decoder built using AtkMatrixMix & AtkKernelConv

FoaDecode {
	*ar { arg in, decoder, mul = 1, add = 0;

		switch ( decoder.class, 

			FoaDecoderMatrix, {

				if ( decoder.shelfFreq.isNumber, { // shelf filter?
					in = FoaPsychoShelf.ar(in.at(0), in.at(1), in.at(2), in.at(3),
						decoder.shelfFreq, decoder.shelfK.at(0), decoder.shelfK.at(1))
				});

				^AtkMatrixMix.ar(in, decoder.matrix, mul, add)
			},
			
			FoaDecoderKernel, {
				^AtkKernelConv.ar(in, decoder.kernel, mul, add)
			}
		)
	}
}


//------------------------------------------------------------------------
// Encoder built using AtkMatrixMix & AtkKernelConv

FoaEncode {
	*ar { arg in, encoder, mul = 1, add = 0;
		
		var out;

		switch ( encoder.class, 

			FoaEncoderMatrix, {
				out = AtkMatrixMix.ar(in, encoder.matrix, mul, add)
			},
			
			FoaEncoderKernel, {
				out = AtkKernelConv.ar(in, encoder.kernel, mul, add)
			}
		);

		if ( out.size < 4, {			// 1st order, fill missing harms with zeros
			out = out ++ Silent.ar(4 - out.size)
		});
		
		^out
	}
}


//------------------------------------------------------------------------
// Transformer built using AtkMatrixMix & AtkKernelConv

FoaXform {
	*ar { arg in, xformer, mul = 1, add = 0;
		
		var out;

//		switch ( xformer.class,
//
//			FoaXformerMatrix, {
//				out = AtkMatrixMix.ar(in, xformer.matrix, mul, add)
//			},
//			
//			FoaXformerKernel, {
//				out = AtkKernelConv.ar(in, xformer.kernel, mul, add)
//			}
//		);
//
//		^out

		// for now...
		^AtkMatrixMix.ar(in, xformer.matrix, mul, add)
	}
}


//------------------------------------------------------------------------
// Transformer: UGen wrapper

FoaTransform {
	*ar { arg in, kind ... args;
		
		var argDict, argDefaults;
		var ugen;
		
		argDict = { arg ugen, args, argDefaults;
			var index, userDict;
			var ugenKeys;
			var ugenDict;
			
			// find index dividing ordered and named args
			index = args.detectIndex({arg item; item.isKindOf(Symbol)});
		
			// find ugen args, drop [ 'this', w, x, y, z ]
			ugenKeys = ugen.class.findRespondingMethodFor(\ar).argNames.drop(5);
		
			ugenDict = Dictionary.new;
			ugenKeys.do({arg key, i; ugenDict.put(key, argDefaults.at(i))});
			
			// build user dictionary
			userDict = Dictionary.new(ugenKeys.size);
			(index == nil).not.if({
				userDict = userDict.putAll(Dictionary.newFrom(args[index..]));
			}, {
				index = args.size;
			});
			userDict = userDict.putAll(Dictionary.newFrom((index).collect({arg i;
				[ugenKeys.at(i), args.at(i)]}).flat));
				
			// merge
			ugenDict.merge(userDict, {
				arg ugenArg, userArg; (userArg != nil).if({userArg})
			})
		};
		

		switch ( kind, 

			'rotate', {

				ugen = FoaRotate;
				argDefaults = [0, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\angle), argDict.at(\mul), argDict.at(\add)
				)
			},
			
			'tilt', {

				ugen = FoaTilt;
				argDefaults = [0, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\angle), argDict.at(\mul), argDict.at(\add)
				)
			},
			
			'tumble', {

				ugen = FoaTumble;
				argDefaults = [0, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\angle), argDict.at(\mul), argDict.at(\add)
				)
			},
				
			'directO', {

				ugen = FoaDirectO;
				argDefaults = [0, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\angle), argDict.at(\mul), argDict.at(\add)
				)
			},

			'directX', {

				ugen = FoaDirectX;
				argDefaults = [0, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\angle), argDict.at(\mul), argDict.at(\add)
				)
			},

			'directY', {

				ugen = FoaDirectY;
				argDefaults = [0, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\angle), argDict.at(\mul), argDict.at(\add)
				)
			},

			'directZ', {

				ugen = FoaDirectZ;
				argDefaults = [0, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\angle), argDict.at(\mul), argDict.at(\add)
				)
			},

			'dominateX', {

				ugen = FoaDominateX;
				argDefaults = [0, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\gain), argDict.at(\mul), argDict.at(\add)
				)
			},
			
			'dominateY', {

				ugen = FoaDominateY;
				argDefaults = [0, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\gain), argDict.at(\mul), argDict.at(\add)
				)
			},

			'dominateZ', {

				ugen = FoaDominateZ;
				argDefaults = [0, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\gain), argDict.at(\mul), argDict.at(\add)
				)
			},

			'zoomX', {

				ugen = FoaZoomX;
				argDefaults = [0, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\angle), argDict.at(\mul), argDict.at(\add)
				)
			},
			
			'zoomY', {

				ugen = FoaZoomY;
				argDefaults = [0, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\angle), argDict.at(\mul), argDict.at(\add)
				)
			},

			'zoomZ', {

				ugen = FoaZoomZ;
				argDefaults = [0, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\angle), argDict.at(\mul), argDict.at(\add)
				)
			},
			
			'focusX', {

				ugen = FoaFocusX;
				argDefaults = [0, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\angle), argDict.at(\mul), argDict.at(\add)
				)
			},

			'focusY', {

				ugen = FoaFocusY;
				argDefaults = [0, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\angle), argDict.at(\mul), argDict.at(\add)
				)
			},

			'focusZ', {

				ugen = FoaFocusZ;
				argDefaults = [0, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\angle), argDict.at(\mul), argDict.at(\add)
				)
			},

			'pushX', {

				ugen = FoaPushX;
				argDefaults = [0, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\angle), argDict.at(\mul), argDict.at(\add)
				)
			},

			'pushY', {

				ugen = FoaPushY;
				argDefaults = [0, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\angle), argDict.at(\mul), argDict.at(\add)
				)
			},

			'pushZ', {

				ugen = FoaPushZ;
				argDefaults = [0, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\angle), argDict.at(\mul), argDict.at(\add)
				)
			},

			'pressX', {

				ugen = FoaPressX;
				argDefaults = [0, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\angle), argDict.at(\mul), argDict.at(\add)
				)
			},

			'pressY', {

				ugen = FoaPressY;
				argDefaults = [0, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\angle), argDict.at(\mul), argDict.at(\add)
				)
			},

			'pressZ', {

				ugen = FoaPressZ;
				argDefaults = [0, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\angle), argDict.at(\mul), argDict.at(\add)
				)
			},

			'asymmetry', {

				ugen = FoaAsymmetry;
				argDefaults = [0, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\angle), argDict.at(\mul), argDict.at(\add)
				)
			},

			'balance', {

				ugen = FoaZoomY;
				argDefaults = [0, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\angle), argDict.at(\mul), argDict.at(\add)
				)
			},

			'rtt', {

				ugen = FoaRTT;
				argDefaults = [0, 0, 0, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\rotAngle), argDict.at(\tilAngle), argDict.at(\tumAngle),
					argDict.at(\mul), argDict.at(\add)
				)
			},
			
			'mirror', {

				ugen = FoaMirror;
				argDefaults = [0, 0, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\theta), argDict.at(\phi),
					argDict.at(\mul), argDict.at(\add)
				)
			},

			'direct', {

				ugen = FoaDirect;
				argDefaults = [0, 0, 0, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\angle), argDict.at(\theta), argDict.at(\phi),
					argDict.at(\mul), argDict.at(\add)
				)
			},

			'dominate', {

				ugen = FoaDominate;
				argDefaults = [0, 0, 0, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\gain), argDict.at(\theta), argDict.at(\phi),
					argDict.at(\mul), argDict.at(\add)
				)
			},

			'zoom', {

				ugen = FoaZoom;
				argDefaults = [0, 0, 0, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\angle), argDict.at(\theta), argDict.at(\phi),
					argDict.at(\mul), argDict.at(\add)
				)
			},

			'focus', {

				ugen = FoaFocus;
				argDefaults = [0, 0, 0, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\angle), argDict.at(\theta), argDict.at(\phi),
					argDict.at(\mul), argDict.at(\add)
				)
			},

			'push', {

				ugen = FoaPush;
				argDefaults = [0, 0, 0, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\angle), argDict.at(\theta), argDict.at(\phi),
					argDict.at(\mul), argDict.at(\add)
				)
			},

			'press', {

				ugen = FoaPress;
				argDefaults = [0, 0, 0, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\angle), argDict.at(\theta), argDict.at(\phi),
					argDict.at(\mul), argDict.at(\add)
				)
			},
			
			'nfc', {

				ugen = FoaNFC;
				argDefaults = [1, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\distance),
					argDict.at(\mul), argDict.at(\add)
				)
			},

			'proximity', {

				ugen = FoaProximity;
				argDefaults = [1, 1, 0];
				
				argDict = argDict.value(ugen, args, argDefaults);
				
				^ugen.ar(
					in.at(0), in.at(1), in.at(2), in.at(3),
					argDict.at(\distance),
					argDict.at(\mul), argDict.at(\add)
				)
			}
		)
	}
}