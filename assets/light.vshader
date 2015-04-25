uniform mat4 uMVPMatrix;

uniform vec3 aLightPosition;//ɢ���λ��
uniform vec3 aEyePosition;//�۾�λ��
uniform mat4 uMMatrix;//ģ�;���

attribute vec3 aPosition;	//λ��
attribute vec3 aNormal;		//������

varying vec3 vNormal;
varying vec3 vPosition;
void main(){
	gl_Position = uMVPMatrix * vec4(aPosition, 1);
	vNormal = aNormal;
	vPosition = aPosition;
}