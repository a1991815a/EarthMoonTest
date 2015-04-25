precision mediump float;

varying vec4 vColor;
varying vec2 vTextureCoord;
uniform sampler2D uTexture0;
uniform sampler2D uTexture1;

void main(){
	vec3 color;
	if(vTextureCoord.x+vTextureCoord.y < 1.0) {//����ʱΪ��ɫ
   		color = vec3(0.678,0.231,0.129);//��ɫ
   }
   else {//ż��ʱΪ��ɫ
   		color = vec3(1.0,1.0,1.0);//��ɫ
   }
	gl_FragColor = vec4(color, 0);
}