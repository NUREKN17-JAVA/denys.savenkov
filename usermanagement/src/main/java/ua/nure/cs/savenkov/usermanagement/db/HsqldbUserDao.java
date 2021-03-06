package ua.nure.cs.savenkov.usermanagement.db;

import java.util.Collection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import ua.nure.cs.savenkov.usermanagement.User;

class HsqldbUserDao implements UserDao {

	private static final String DELETE_QUERY = "DELETE FROM users WHERE id=?";
	private static final String SELECT_QUERY = "SELECT * FROM users WHERE id=?";
	private static final String SELECT_ALL_QUERY = "SELECT * FROM users";
	private ConnectionFactory connectionFactory;
	private static final String CALL_IDENTITY = "call IDENTITY()";
	private static final String INSERT_QUERY = "INSERT INTO users (firstname, lastname, dateofbirth) VALUES (?,?,?)";
	private static final String UPDATE_QUERY = "UPDATE users SET firstname = ?, lastname = ?, dateofbirth = ? WHERE id = ?";
	private static final String SELECT_BY_NAMES_QUERY = "SELECT id, firstname, lastname, dateofbirth FROM users WHERE firstname=? AND lastname=?";

	public HsqldbUserDao() {
	}

	public HsqldbUserDao(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	@Override
	public User create(User entity) throws DataBaseException {
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
			preparedStatement.setString(1, entity.getFirstName());
			preparedStatement.setString(2, entity.getLastName());
			preparedStatement.setDate(3, new Date(entity.getDateOfBirth().getTime()));
			int numberOfRows = preparedStatement.executeUpdate();
			if (numberOfRows != 1) {
				throw new DataBaseException("Number of rows: " + numberOfRows);
			}
			CallableStatement callableStatement = connection.prepareCall(CALL_IDENTITY);
			ResultSet keys = callableStatement.executeQuery();
			if (keys.next()) {
				entity.setId(keys.getLong(1));
			}
			keys.close();
			callableStatement.close();
			preparedStatement.close();
			connection.close();
		} catch (DataBaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DataBaseException(e);
		}
		return entity;
	}

	@Override
	public void update(User entity) throws DataBaseException {
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
			preparedStatement.setString(1, entity.getFirstName());
			preparedStatement.setString(2, entity.getLastName());
			preparedStatement.setDate(3, new Date(entity.getDateOfBirth().getTime()));
			preparedStatement.setLong(4, entity.getId());
			int numberOfRows = preparedStatement.executeUpdate();
			if (numberOfRows != 1) {
				throw new DataBaseException("Number of updated rows: " + numberOfRows);
			}
			preparedStatement.close();
			connection.close();
		} catch (DataBaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DataBaseException(e);
		}
	}

	@Override
	public void delete(User entity) throws DataBaseException {
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
			preparedStatement.setLong(1, entity.getId());
			int numberOfRows = preparedStatement.executeUpdate();
			if (numberOfRows != 1) {
				throw new DataBaseException("Number of deleted rows: " + numberOfRows);
			}
			preparedStatement.close();
			connection.close();
		} catch (DataBaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DataBaseException(e);
		}

	}

	@Override
	public User find(Long id) throws DataBaseException {
		User entity = null;
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
			preparedStatement.setLong(1, id);
			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
				entity = new User();
				entity.setId(result.getLong(1));
				entity.setFirstName(result.getString(2));
				entity.setLastName(result.getString(3));
				entity.setDateOfBirth(result.getDate(4));
			}
			result.close();
			preparedStatement.close();
			connection.close();
		} catch (DataBaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DataBaseException(e);
		}
		return entity;
	}

	@Override
	public Collection<User> findAll() throws DataBaseException {
		Collection<User> result = new LinkedList<User>();

		try {
			Connection connection = connectionFactory.createConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);

			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getLong(1));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setDateOfBirth(resultSet.getDate(4));

				result.add(user);
			}

			resultSet.close();
			statement.close();
			connection.close();
		} catch (DataBaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DataBaseException(e);
		}
		return result;
	}

	@Override
	public Collection find(String firstName, String lastName) throws DataBaseException {
		Collection<User> result = new LinkedList<User>();

		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_NAMES_QUERY);
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getLong(1));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setDateOfBirth(resultSet.getDate(4));

				result.add(user);
			}

			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (DataBaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DataBaseException(e);
		}
		return result;
	}

}