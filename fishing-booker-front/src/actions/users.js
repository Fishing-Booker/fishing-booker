import userRequests from "./userRequests";

export const ACTIONS = {
    GET_ALL: 'GET_ALL',
    GET_BY_ID: 'GET_BY_ID',
    ADD: 'ADD',
    UPDATE: 'UPDATE',
    DELETE: 'DELETE'
}

const formateData = data => ({
    ...data
})

export const getAll = () => dispatch => {
    userRequests.users().getAll()
        .then(response => {
            console.log(response);
            dispatch({
                type: ACTIONS.GET_ALL,
                payload: response.data
            })
        })
        .catch(err => console.log(err))
}

export const getById = (id) => dispatch => {
    userRequests.users().getById(id)
        .then(response => {
            console.log(response);
            dispatch({
                type: ACTIONS.GET_BY_ID,
                payload: response.data
            })
        })
        .catch(err => console.log(err))
}

export const add = (data, onSuccess) => dispatch => {
    data = formateData(data)
    userRequests.users().addUser(data)
        .then(response => {
            dispatch({
                type: ACTIONS.ADD,
                payload: response.data
            })
            onSuccess()
        })
        .catch(err => console.log(err))
}

export const update = (id, data, onSuccess) => dispatch => {
    data = formateData(data)
    userRequests.users().updateUser(id, data)
        .then(response => {
            dispatch({
                type: ACTIONS.UPDATE,
                payload: response.data
            })
            onSuccess()
        })
        .catch(err => console.log(err))
}

export const remove = (id, onSuccess) => dispatch => {
    userRequests.users().deleteUser(id)
        .then(response => {
            dispatch({
                type: ACTIONS.DELETE,
                payload: response.data
            })
            onSuccess()
        })
        .catch(err => console.log(err))
}