/*
Copyright (c) Microsoft Open Technologies, Inc.
All Rights Reserved
Apache 2.0 License
 
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
     http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 
See the Apache Version 2.0 License for specific language governing permissions and limitations under the License.
 */
package com.microsoft.services.sharepoint;

import java.security.InvalidParameterException;
import java.util.Locale;

/**
 * Class used to create query operations
 */
public class QueryOperations {

	/**
	 * Creates a Query representing a function call
	 * 
	 * @param functionName
	 *            The function name
	 * @param parameters
	 *            The function parameters
	 * @return The Query representing a function call
	 */
	private static Query function(String functionName,
			Query... parameters) {

		Query query = new Query();

		query.setQueryText(functionName);

		for (Query p : parameters) {
			query.addInternalValue(p);
		}

		return query;
	}

	/**
	 * Creates a Query representing an operator
	 * 
	 * @param otherQuery
	 *            The query to operateWith
	 * @param operator
	 *            The operator
	 * @return The Query representing an operation
	 */
	private static Query simpleOperator(
			Query otherQuery, String operator) {
		Query query = new Query();

		query.setQueryText(operator + " ");

		if (otherQuery != null) {
			query.addInternalValue(otherQuery);
		}

		return query;
	}

	/**
	 * Sanitizes the string to use in a oData query
	 * 
	 * @param s
	 *            The string to sanitize
	 * @return The sanitized string
	 */
	private static String sanitize(String s) {
		if (s != null) {
			return s.replace("'", "''");
		} else {
			return null;
		}
	}

    /**
     * Creates Query with an existing query as its only internal
     * value
     *
     * @param subQuery the sub query
     * @return The Query
     */
	public static Query query(Query subQuery) {
		Query query = new Query();

		query.addInternalValue(subQuery);

		return query;
	}

    /**
     * Creates Query representing a field
     *
     * @param fieldName             The name of the field
     * @return The Query
     */
	public static Query field(String fieldName) {
		if (fieldName == null || fieldName.trim().length() == 0) {
			throw new InvalidParameterException(
					"fieldName cannot be null or empty");
		}

		Query query = new Query();

		query.setQueryText(fieldName);

		return query;
	}

    /**
     * Creates a Query representing a numeric value
     *
     * @param number             the number to represent
     * @return the Query
     */
	public static Query val(Number number) {
		Query query = new Query();

		if (number == null) {
			query.setQueryText("null");
		} else {
			query.setQueryText(number.toString());
		}

		return query;
	}

    /**
     * Creates a Query representing a boolean value
     *
     * @param val             the boolean to represent
     * @return the Query
     */
	public static Query val(boolean val) {
		Query query = new Query();

		query.setQueryText(Boolean.valueOf(val).toString()
				.toLowerCase(Locale.getDefault()));

		return query;
	}

    /**
     * Creates a Query representing a string value
     *
     * @param s the string to represent
     * @return the Query
     */
	public static Query val(String s) {

		Query query = new Query();

		if (s == null) {
			query.setQueryText("null");
		} else {
			query.setQueryText("'" + sanitize(s) + "'");
		}

		return query;
	}

	/****** Logical Operators ******/

    /**
     * Conditional and.
     *
     * @return Query query
     */
	public static Query and() {
		return and(null);
	}

    /**
     * Conditional and.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public static Query and(Query otherQuery) {
		return simpleOperator(otherQuery, "and");
	}

    /**
     * Conditional or.
     *
     * @return Query query
     */
	public static Query or() {
		return or(null);
	}

    /**
     * Conditional or.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public static Query or(Query otherQuery) {
		return simpleOperator(otherQuery, "or");
	}

    /**
     * Logical not.
     *
     * @return Query query
     */
	public static Query not() {
		return not(null);
	}

    /**
     * Logical not.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public static Query not(Query otherQuery) {
		return simpleOperator(otherQuery, "not");
	}

    /**
     * Logical not.
     *
     * @param booleanValue the boolean value
     * @return Query query
     */
	public static Query not(boolean booleanValue) {

		return not(val(booleanValue));
	}

	/****** Comparison Operators ******/

    /**
     * Greater than or equal comparison operator.
     *
     * @return Query query
     */
	public static Query ge() {
		Query nullQuery = null;
		return ge(nullQuery);
	}

    /**
     * Greater than or equal comparison operator.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public static Query ge(Query otherQuery) {
		return simpleOperator(otherQuery, "ge");
	}

    /**
     * Greater than or equal comparison operator.
     *
     * @param numberValue the number value
     * @return Query query
     */
	public static Query ge(Number numberValue) {
		return ge(QueryOperations.val(numberValue));
	}


    /**
     * Less than or equal comparison operator.
     *
     * @return Query query
     */
	public static Query le() {
		Query nullQuery = null;
		return le(nullQuery);
	}

    /**
     * Less than or equal comparison operator.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public static Query le(Query otherQuery) {
		return simpleOperator(otherQuery, "le");
	}

    /**
     * Less than or equal comparison operator.
     *
     * @param numberValue the number value
     * @return Query query
     */
	public static Query le(Number numberValue) {
		return le(QueryOperations.val(numberValue));
	}

    /**
     * Greater than comparison operator.
     *
     * @return Query query
     */
	public static Query gt() {
		Query nullQuery = null;
		return gt(nullQuery);
	}

    /**
     * Greater than comparison operator.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public static Query gt(Query otherQuery) {
		return simpleOperator(otherQuery, "gt");
	}

    /**
     * Greater than comparison operator.
     *
     * @param numberValue the number value
     * @return Query query
     */
	public static Query gt(Number numberValue) {
		return gt(QueryOperations.val(numberValue));
	}

    /**
     * Less than comparison operator.
     *
     * @return Query query
     */
	public static Query lt() {
		Query nullQuery = null;
		return lt(nullQuery);
	}

    /**
     * Less than comparison operator.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public static Query lt(Query otherQuery) {
		return simpleOperator(otherQuery, "lt");
	}

    /**
     * Less than comparison operator.
     *
     * @param numberValue the number value
     * @return Query query
     */
	public static Query lt(Number numberValue) {
		return lt(QueryOperations.val(numberValue));
	}

    /**
     * Equal comparison operator.
     *
     * @return Query query
     */
	public static Query eq() {
		Query nullQuery = null;
		return eq(nullQuery);
	}

    /**
     * Equal comparison operator.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public static Query eq(Query otherQuery) {
		return simpleOperator(otherQuery, "eq");
	}

    /**
     * Equal comparison operator.
     *
     * @param numberValue the number value
     * @return Query query
     */
	public static Query eq(Number numberValue) {
		return eq(QueryOperations.val(numberValue));
	}

    /**
     * Equal comparison operator.
     *
     * @param booleanValue the boolean value
     * @return Query query
     */
	public static Query eq(boolean booleanValue) {
		return eq(QueryOperations.val(booleanValue));
	}

    /**
     * Equal comparison operator.
     *
     * @param stringValue the string value
     * @return Query query
     */
	public static Query eq(String stringValue) {
		return eq(QueryOperations.val(stringValue));
	}

    /**
     * Not equal comparison operator.
     *
     * @return Query query
     */
	public static Query ne() {
		Query nullQuery = null;
		return ne(nullQuery);
	}

    /**
     * Not equal comparison operator.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public static Query ne(Query otherQuery) {
		return simpleOperator(otherQuery, "ne");
	}

    /**
     * Not equal comparison operator.
     *
     * @param numberValue the number value
     * @return Query query
     */
	public static Query ne(Number numberValue) {
		return ne(QueryOperations.val(numberValue));
	}

    /**
     * Not equal comparison operator.
     *
     * @param booleanValue the boolean value
     * @return Query query
     */
	public static Query ne(boolean booleanValue) {
		return ne(QueryOperations.val(booleanValue));
	}

    /**
     * Not equal comparison operator.
     *
     * @param stringValue the string value
     * @return Query query
     */
	public static Query ne(String stringValue) {
		return ne(QueryOperations.val(stringValue));
	}

	/****** Arithmetic Operators ******/

    /**
     * Add operator.
     *
     * @return Query query
     */
	public static Query add() {
		Query nullQuery = null;
		return add(nullQuery);
	}

    /**
     * Add operator.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public static Query add(Query otherQuery) {
		return simpleOperator(otherQuery, "add");
	}

    /**
     * Add operator.
     *
     * @param val the val
     * @return Query query
     */
	public static Query add(Number val) {
		return add(val(val));
	}

    /**
     * Subtract operator.
     *
     * @return Query query
     */
	public static Query sub() {
		Query nullQuery = null;
		return sub(nullQuery);
	}

    /**
     * Subtract operator.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public static Query sub(Query otherQuery) {
		return simpleOperator(otherQuery, "sub");
	}

    /**
     * Subtract operator.
     *
     * @param val the val
     * @return Query query
     */
	public static Query sub(Number val) {
		return sub(val(val));
	}

    /**
     * Multiply operator.
     *
     * @return Query query
     */
	public static Query mul() {
		Query nullQuery = null;
		return mul(nullQuery);
	}

    /**
     * Multiply operator.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public static Query mul(Query otherQuery) {
		return simpleOperator(otherQuery, "mul");
	}

    /**
     * Multiply operator.
     *
     * @param val the val
     * @return Query query
     */
	public static Query mul(Number val) {
		return mul(val(val));
	}

    /**
     * Divide operator.
     *
     * @return Query query
     */
	public static Query div() {
		Query nullQuery = null;
		return div(nullQuery);
	}

    /**
     * Divide operator.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public static Query div(Query otherQuery) {
		return simpleOperator(otherQuery, "div");
	}

    /**
     * Divide operator.
     *
     * @param val the val
     * @return Query query
     */
	public static Query div(Number val) {
		return div(val(val));
	}

    /**
     * Reminder (or modulo) operator.
     *
     * @return Query query
     */
	public static Query mod() {
		Query nullQuery = null;
		return mod(nullQuery);
	}

    /**
     * Reminder (or modulo) operator.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public static Query mod(Query otherQuery) {
		return simpleOperator(otherQuery, "mod");
	}

    /**
     * Reminder (or modulo) operator.
     *
     * @param val the val
     * @return Query query
     */
	public static Query mod(Number val) {
		return mod(val(val));
	}

	/****** Date Functions ******/

    /**
     * The year component value of the parameter value.
     *
     * @param exp the exp
     * @return Query query
     */
	public static Query year(Query exp) {
		return function("year", exp);
	}

    /**
     * The year component value of the parameter value.
     *
     * @param field the field
     * @return Query query
     */
	public static Query year(String field) {
		return function("year", field(field));
	}

    /**
     * The month component value of the parameter value.
     *
     * @param exp the exp
     * @return Query query
     */
	public static Query month(Query exp) {
		return function("month", exp);
	}

    /**
     * The month component value of the parameter value.
     *
     * @param field the field
     * @return Query query
     */
	public static Query month(String field) {
		return function("month", field(field));
	}

    /**
     * The day component value of the parameter value.
     *
     * @param exp the exp
     * @return Query query
     */
	public static Query day(Query exp) {
		return function("day", exp);
	}

    /**
     * The day component value of the parameter value.
     *
     * @param field the field
     * @return Query query
     */
	public static Query day(String field) {
		return function("day", field(field));
	}

    /**
     * The hour component value of the parameter value.
     *
     * @param exp the exp
     * @return Query query
     */
	public static Query hour(Query exp) {
		return function("hour", exp);
	}

    /**
     * The hour component value of the parameter value.
     *
     * @param field the field
     * @return Query query
     */
	public static Query hour(String field) {
		return function("hour", field(field));
	}

    /**
     * The minute component value of the parameter value.
     *
     * @param exp the exp
     * @return Query query
     */
	public static Query minute(Query exp) {
		return function("minute", exp);
	}

    /**
     * The minute component value of the parameter value.
     *
     * @param field the field
     * @return Query query
     */
	public static Query minute(String field) {
		return function("minute", field(field));
	}

    /**
     * The second component value of the parameter value.
     *
     * @param exp the exp
     * @return Query query
     */
	public static Query second(Query exp) {
		return function("second", exp);
	}

    /**
     * The second component value of the parameter value.
     *
     * @param field the field
     * @return Query query
     */
	public static Query second(String field) {
		return function("second", field(field));
	}

	/****** Math Functions ******/

    /**
     * The largest integral value less than or equal to the parameter value.
     *
     * @param exp the exp
     * @return Query query
     */
	public static Query floor(Query exp) {
		return function("floor", exp);
	}

    /**
     * The smallest integral value greater than or equal to the parameter value.
     *
     * @param exp the exp
     * @return Query query
     */
	public static Query ceiling(Query exp) {
		return function("ceiling", exp);
	}

    /**
     * The nearest integral value to the parameter value.
     *
     * @param exp the exp
     * @return Query query
     */
	public static Query round(Query exp) {
		return function("round", exp);
	}

	/****** String Functions ******/

    /**
     * String value with the contents of the parameter value converted to lower
     * case.
     *
     * @param exp the exp
     * @return Query query
     */
	public static Query toLower(Query exp) {
		return function("tolower", exp);
	}

    /**
     * String value with the contents of the parameter value converted to lower
     * case.
     *
     * @param field the field
     * @return Query query
     */
	public static Query toLower(String field) {
		return toLower(field(field));
	}

    /**
     * String value with the contents of the parameter value converted to upper
     * case
     *
     * @param exp the exp
     * @return Query query
     */
	public static Query toUpper(Query exp) {
		return function("toupper", exp);
	}

    /**
     * String value with the contents of the parameter value converted to upper
     * case
     *
     * @param field the field
     * @return Query query
     */
	public static Query toUpper(String field) {
		return toUpper(field(field));
	}

    /**
     * The number of characters in the specified parameter value.
     *
     * @param exp the exp
     * @return Query query
     */
	public static Query length(Query exp) {
		return function("length", exp);
	}

    /**
     * The number of characters in the specified parameter value.
     *
     * @param field the field
     * @return Query query
     */
	public static Query length(String field) {
		return length(field(field));
	}

    /**
     * String value with the contents of the parameter value with all leading
     * and trailing white-space characters removed.
     *
     * @param exp the exp
     * @return Query query
     */
	public static Query trim(Query exp) {
		return function("trim", exp);
	}

    /**
     * String value with the contents of the parameter value with all leading
     * and trailing white-space characters removed.
     *
     * @param field the field
     * @return Query query
     */
	public static Query trim(String field) {
		return trim(field(field));
	}

    /**
     * Whether the beginning of the first parameter values matches the second
     * parameter value.
     *
     * @param field             The field to evaluate.
     * @param start             Start value.
     * @return Query query
     */
	public static Query startsWith(Query field,
			Query start) {
		return function("startswith", field, start);
	}

    /**
     * Whether the beginning of the first parameter values matches the second
     * parameter value.
     *
     * @param field             The field to evaluate.
     * @param start             Start value.
     * @return Query query
     */
	public static Query startsWith(String field, String start) {
		return startsWith(field(field), val(start));
	}

    /**
     * Whether the end of the first parameter value matches the second parameter
     * value.
     *
     * @param field             The field to evaluate.
     * @param end             End value.
     * @return Query query
     */
	public static Query endsWith(Query field,
			Query end) {
		return function("endsWith", field, end);
	}

    /**
     * Whether the end of the first parameter value matches the second parameter
     * value.
     *
     * @param field             The field to evaluate.
     * @param end             End value.
     * @return Query query
     */
	public static Query endsWith(String field, String end) {
		return endsWith(field(field), val(end));
	}

    /**
     * Whether the first parameter string value occurs in the second parameter
     * string value.
     *
     * @param str1             First string
     * @param str2             Second string
     * @return Query query
     */
	public static Query subStringOf(Query str1,
			Query str2) {
		return function("substringof", str1, str2);
	}

    /**
     * Whether the string parameter occurs in the field
     *
     * @param str             String to search
     * @param field             Field to search in
     * @return Query query
     */
	public static Query subStringOf(String str, String field) {
		return subStringOf(val(str), field(field));
	}

    /**
     * String value which is the first and second parameter values merged
     * together with the first parameter value coming first in the result.
     *
     * @param str1             First string
     * @param str2             Second string
     * @return Query query
     */
	public static Query concat(Query str1,
			Query str2) {
		return function("concat", str1, str2);
	}

    /**
     * Index of the first occurrence of the second parameter value in the first
     * parameter value or -1 otherwise.
     *
     * @param haystack             String content
     * @param needle             Value to search for
     * @return Query query
     */
	public static Query indexOf(Query haystack,
			Query needle) {
		return function("indexof", haystack, needle);
	}

    /**
     * Index of the first occurrence of the second parameter value in the first
     * parameter value or -1 otherwise.
     *
     * @param field             Field to seach in
     * @param str             Value to search for
     * @return Query query
     */
	public static Query indexOf(String field, String str) {
		return indexOf(field(field), val(str));
	}

    /**
     * String value starting at the character index specified by the second
     * parameter value in the first parameter string value.
     *
     * @param str             String content
     * @param pos             Starting position
     * @return Query query
     */
	public static Query subString(Query str,
			Query pos) {
		return function("substring", str, pos);
	}

    /**
     * String value starting at the character index specified by the second
     * parameter value in the first parameter string value.
     *
     * @param field             Field to scan
     * @param pos             Starting position
     * @return Query query
     */
	public static Query subString(String field, int pos) {
		return subString(field(field), val(pos));
	}

    /**
     * String value starting at the character index specified by the second
     * parameter value in the first parameter string value.
     *
     * @param str             String content
     * @param pos             Starting position
     * @param length             Length
     * @return Query query
     */
	public static Query subString(Query str,
			Query pos, Query length) {
		return function("substring", str, pos, length);
	}

    /**
     * String value starting at the character index specified by the second
     * parameter value in the first parameter string value.
     *
     * @param field             Field to scan
     * @param pos             Starting position
     * @param length             Length
     * @return Query query
     */
	public static Query subString(String field, int pos,
			int length) {
		return subString(field(field), val(pos), val(length));
	}

    /**
     * Finds the second string parameter in the first parameter string value and
     * replaces it with the third parameter value.
     *
     * @param str             String content
     * @param find             Search value
     * @param replace             Replace value
     * @return Query query
     */
	public static Query replace(Query str,
			Query find, Query replace) {
		return function("replace", str, find, replace);
	}

    /**
     * Finds the second string parameter in the first parameter string value and
     * replaces it with the third parameter value.
     *
     * @param field             Field to scan
     * @param find             Search value
     * @param replace             Replace value
     * @return Query query
     */
	public static Query replace(String field, String find,
			String replace) {
		return replace(field(field), val(find), val(replace));
	}
}
