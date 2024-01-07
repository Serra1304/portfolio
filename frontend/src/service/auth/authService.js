/**
 * Realiza la autenticación hacia la API utilizando las credenciales proporcionadas.
 * @async
 * @returns {Promise<string>} El token de autenticación si la autenticación es exitosa.
 * @throws {Error} Si ocurre un error durante el proceso de autenticación.
 */
const authentication = process.env.REACT_APP_API_AUTHENTICATION;
const authUser = process.env.REACT_APP_API_AUTHENTICATION_USER;
const authPass = process.env.REACT_APP_API_AUTHENTICATION_PASS;

async function authToApi() {
    try {
        const response = await fetch(authentication, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ user: authUser, password: authPass }),
        });

        const responseData = await response.json();

        if (!response.ok) {
            const errorMessage = responseData.message || 'Error al autenticarse';
            throw new Error(errorMessage);
        }

        return responseData.token;
    } catch (error) {
        console.error('Error de red:', error);
        throw error;
    }
}

export default authToApi;
