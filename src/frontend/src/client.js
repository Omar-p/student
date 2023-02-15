import fetch from 'unfetch';

const checkStatus = response => {
    if (response.ok) {
        return response
    }

    const error = new Error(response.statusText)
    error.respone = response
    return Promise.reject(error)
}

export const getAllStudents = () =>
    fetch('api/v1/students')
        .then(checkStatus)
