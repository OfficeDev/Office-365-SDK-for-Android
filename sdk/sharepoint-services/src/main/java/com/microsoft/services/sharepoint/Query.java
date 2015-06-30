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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.util.Pair;

/**
 * Class that represents a query
 */
public final class Query {

	/**
	 * The main text of the query
	 */
	private String mQueryText = null;

	/**
	 * Indicates if the query should include the inlinecount property
	 */
	private boolean mHasInlineCount = false;

	/**
	 * Query ordering to use
	 */
	private List<Pair<String, QueryOrder>> mOrderBy = new ArrayList<Pair<String, QueryOrder>>();

	/**
	 * Query projection to use
	 */
	private List<String> mProjection = new ArrayList<String>();

	/**
	 * Query expand to use
	 */
	private List<String> mExpand = new ArrayList<String>();

	/**
	 * User-defined properties to use
	 */
	private List<Pair<String, String>> mUserDefinedParameters = new ArrayList<Pair<String, String>>();

	/**
	 * Top rows to retrieve
	 */
	private int mTop = -1;

	/**
	 * Rows to skip
	 */
	private int mSkip = -1;

	/**
	 * List of values to print between parentheses
	 */
	private List<Query> internalValues = new ArrayList<Query>();

	/**
	 * Next steps in the query
	 */
	private List<Query> querySteps = new ArrayList<Query>();

    /**
     * Ensure id property.
     */
    public void ensureIdProperty() {
		if (mProjection.size() > 0) {
			for (String projection : mProjection) {
				if (projection.toLowerCase(Locale.getDefault()).equals("id")) {
					return;
				}
			}
			mProjection.add("id");
		}
	}

    /**
     * Returns the main text of the query
     * @return the query text
     */
	public String getQueryText() {
		return mQueryText;
	}

    /**
     * Sets the main text of the query
     *
     * @param queryText The text to set
     */
	public void setQueryText(String queryText) {
		this.mQueryText = queryText;
	}

    /**
     * Creates an empty Query
     */
	public Query() {

	}

    /**
     * Creates Query with an existing query as its only internal value
     *
     * @param query The query step to add
     */
	Query(Query query) {
		internalValues.add(query);
	}

    /**
     * Adds an internal value to the query
     *
     * @param query The value to add
     */
	void addInternalValue(Query query) {
		internalValues.add(query);
	}

	/**
	 * Returns the string representation of the query
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		if (getQueryText() != null) {
			sb.append(getQueryText());
		}

		if (internalValues.size() > 0) {
			sb.append("(");

			boolean first = true;
			for (Query val : internalValues) {
				if (first) {
					first = false;
				} else {
					sb.append(",");
				}

				sb.append(val.toString());
			}

			sb.append(")");
		}

		for (Query step : querySteps) {
			// If the string is not empty and it doesn't end with space or
			// if it ends with ")", then add a space
			if ((!sb.toString().endsWith(" ") && sb.toString().length() > 0)
					|| sb.toString().endsWith(")")) {
				sb.append(" ");
			}

			sb.append(step.toString());
		}

		return sb.toString();
	}

    /**
     * Returns the string representation of the rowset's modifiers
     * @return the row set modifiers
     */
	public String getRowSetModifiers() {
		StringBuilder sb = new StringBuilder();

		try {
			if (this.mHasInlineCount) {
				sb.append("&$inlinecount=allpages");
			}

			if (this.mTop > 0) {
				sb.append("&$top=");
				sb.append(this.mTop);
			}

			if (this.mSkip > 0) {
				sb.append("&$skip=");
				sb.append(this.mSkip);
			}

			if (this.mOrderBy.size() > 0) {
				sb.append("&$orderby=");

				boolean first = true;
				for (Pair<String, QueryOrder> order : this.mOrderBy) {
					if (first) {
						first = false;
					} else {
						sb.append(URLEncoder.encode(",", Constants.UTF8_NAME));
					}

					sb.append(URLEncoder.encode(order.first, Constants.UTF8_NAME));
					sb.append(URLEncoder.encode(" ", Constants.UTF8_NAME));
					sb.append(order.second == QueryOrder.Ascending ? "asc" : "desc");

				}
			}

			if (!this.mUserDefinedParameters.isEmpty()) {
				for (Pair<String, String> parameter : this.mUserDefinedParameters) {
					if (parameter.first != null) {
						sb.append("&");

						String key = parameter.first;
						String value = parameter.second;
						if (value == null)
							value = "null";

						sb.append(URLEncoder.encode(key, Constants.UTF8_NAME));
						sb.append("=");
						sb.append(URLEncoder.encode(value, Constants.UTF8_NAME));
					}
				}
			}

			if (this.mProjection != null && this.mProjection.size() > 0) {
				sb.append("&$select=");

				boolean first = true;
				for (String field : this.mProjection) {
					if (first) {
						first = false;
					} else {
						sb.append(URLEncoder.encode(",", Constants.UTF8_NAME));
					}

					sb.append(URLEncoder.encode(field, Constants.UTF8_NAME));
				}
			}

			if (this.mExpand != null && this.mExpand.size() > 0) {
				sb.append("&$expand=");

				boolean first = true;
				for (String field : this.mExpand) {
					if (first) {
						first = false;
					} else {
						sb.append(URLEncoder.encode(",", Constants.UTF8_NAME));
					}

					sb.append(URLEncoder.encode(field, Constants.UTF8_NAME));
				}
			}
		} catch (UnsupportedEncodingException e) {
			// this never happens with utf8, it's safe to ignore
		}

		return sb.toString();
	}

	/**** Row Operations ****/

    /**
     * Adds a new user-defined parameter to the query
     *
     * @param parameter The parameter name
     * @param value The parameter value
     * @return Query query
     */
	public Query parameter(String parameter, String value) {
		this.mUserDefinedParameters.add(new Pair<String, String>(parameter, value));
		return this;
	}

    /**
     * Adds a new order by statement
     *
     * @param field FieldName
     * @param order Sorting order
     * @return Query query
     */
	public Query orderBy(String field, QueryOrder order) {
		this.mOrderBy.add(new Pair<String, QueryOrder>(field, order));
		return this;
	}

    /**
     * Sets the number of records to return
     *
     * @param top Number of records to return
     * @return Query query
     */
	public Query top(int top) {
		if (top > 0) {
			this.mTop = top;
		}

		return this;
	}

    /**
     * Sets the number of records to skip over a given number of elements in a
     * sequence and then return the remainder.
     *
     * @param skip the skip
     * @return Query query
     */
	public Query skip(int skip) {
		if (skip > 0) {
			this.mSkip = skip;
		}

		return this;
	}

    /**
     * The inlinecount property specifies whether or not to retrieve a property
     * with the number of records returned.
     *
     * @return Query query
     */
	public Query includeInlineCount() {
		this.mHasInlineCount = true;

		return this;
	}

    /**
     * Specifies the fields to retrieve
     *
     * @param fields Names of the fields to retrieve
     * @return Query query
     */
	public Query select(String... fields) {
		this.mProjection = new ArrayList<String>();
		for (String field : fields) {
			this.mProjection.add(field);
		}

		return this;
	}

    /**
     * Specifies the fields to expand
     *
     * @param fields Names of the fields to expand
     * @return Query query
     */
	public Query expand(String... fields) {
		this.mExpand = new ArrayList<String>();
		for (String field : fields) {
			this.mExpand.add(field);
		}

		return this;
	}

	/**** Query Operations ****/

    /**
     * Specifies the field to use
     *
     * @param fieldName The field to use
     * @return Query query
     */
	public Query field(String fieldName) {
		this.querySteps.add(QueryOperations.field(fieldName));
		return this;
	}

    /**
     * Specifies a numeric value
     *
     * @param number The numeric value to use
     * @return Query query
     */
	public Query val(Number number) {
		this.querySteps.add(QueryOperations.val(number));
		return this;
	}

    /**
     * Val query.
     *
     * @param val the val
     * @return the query
     */
    public Query val(boolean val) {
		this.querySteps.add(QueryOperations.val(val));
		return this;
	}


    /**
     * Val query.
     *
     * @param s the s
     * @return the query
     */
    public Query val(String s) {
		this.querySteps.add(QueryOperations.val(s));
		return this;
	}

	/****** Logical Operators ******/

    /**
     * Conditional and.
     *
     * @return Query query
     */
	public Query and() {
		this.querySteps.add(QueryOperations.and());
		return this;
	}

    /**
     * Conditional and.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public Query and(Query otherQuery) {
		this.querySteps.add(QueryOperations.and(otherQuery));
		return this;
	}

    /**
     * Conditional or.
     *
     * @return Query query
     */
	public Query or() {
		this.querySteps.add(QueryOperations.or());
		return this;
	}

    /**
     * Conditional or.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public Query or(Query otherQuery) {
		this.querySteps.add(QueryOperations.or(otherQuery));
		return this;
	}

    /**
     * Logical not.
     *
     * @return Query query
     */
	public Query not() {
		this.querySteps.add(QueryOperations.not());
		return this;
	}

    /**
     * Logical not.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public Query not(Query otherQuery) {
		this.querySteps.add(QueryOperations.not(otherQuery));
		return this;
	}

    /**
     * Logical not.
     *
     * @param booleanValue the boolean value
     * @return Query query
     */
	public Query not(boolean booleanValue) {
		this.querySteps.add(QueryOperations.not(QueryOperations.val(booleanValue)));
		return this;
	}

	/****** Comparison Operators ******/

    /**
     * Greater than or equal comparison operator.
     *
     * @return Query query
     */
	public Query ge() {
		this.querySteps.add(QueryOperations.ge());
		return this;
	}

    /**
     * Greater than or equal comparison operator.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public Query ge(Query otherQuery) {
		this.querySteps.add(QueryOperations.ge(otherQuery));
		return this;
	}

    /**
     * Greater than or equal comparison operator.
     *
     * @param numberValue the number value
     * @return Query query
     */
	public Query ge(Number numberValue) {
		this.querySteps.add(QueryOperations.ge(QueryOperations.val(numberValue)));
		return this;
	}

    /**
     * Less than or equal comparison operator.
     *
     * @return Query query
     */
	public Query le() {
		this.querySteps.add(QueryOperations.le());
		return this;
	}

    /**
     * Less than or equal comparison operator.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public Query le(Query otherQuery) {
		this.querySteps.add(QueryOperations.le(otherQuery));
		return this;
	}

    /**
     * Less than or equal comparison operator.
     *
     * @param numberValue the number value
     * @return Query query
     */
	public Query le(Number numberValue) {
		this.querySteps.add(QueryOperations.le(QueryOperations.val(numberValue)));
		return this;
	}

    /**
     * Greater than comparison operator.
     *
     * @return Query query
     */
	public Query gt() {
		this.querySteps.add(QueryOperations.gt());
		return this;
	}

    /**
     * Greater than comparison operator.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public Query gt(Query otherQuery) {
		this.querySteps.add(QueryOperations.gt(otherQuery));
		return this;
	}

    /**
     * Greater than comparison operator.
     *
     * @param numberValue the number value
     * @return Query query
     */
	public Query gt(Number numberValue) {
		this.querySteps.add(QueryOperations.gt(QueryOperations.val(numberValue)));
		return this;
	}

    /**
     * Less than comparison operator.
     *
     * @return Query query
     */
	public Query lt() {
		this.querySteps.add(QueryOperations.lt());
		return this;
	}

    /**
     * Less than comparison operator.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public Query lt(Query otherQuery) {
		this.querySteps.add(QueryOperations.lt(otherQuery));
		return this;
	}

    /**
     * Less than comparison operator.
     *
     * @param numberValue the number value
     * @return Query query
     */
	public Query lt(Number numberValue) {
		this.querySteps.add(QueryOperations.lt(QueryOperations.val(numberValue)));
		return this;
	}

    /**
     * Equal comparison operator.
     *
     * @return Query query
     */
	public Query eq() {
		this.querySteps.add(QueryOperations.eq());
		return this;
	}

    /**
     * Equal comparison operator.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public Query eq(Query otherQuery) {
		this.querySteps.add(QueryOperations.eq(otherQuery));
		return this;
	}

    /**
     * Equal comparison operator.
     *
     * @param numberValue the number value
     * @return Query query
     */
	public Query eq(Number numberValue) {
		this.querySteps.add(QueryOperations.eq(QueryOperations.val(numberValue)));
		return this;
	}

    /**
     * Equal comparison operator.
     *
     * @param booleanValue the boolean value
     * @return Query query
     */
	public Query eq(boolean booleanValue) {
		this.querySteps.add(QueryOperations.eq(QueryOperations.val(booleanValue)));
		return this;
	}

    /**
     * Equal comparison operator.
     *
     * @param stringValue the string value
     * @return Query query
     */
	public Query eq(String stringValue) {
		this.querySteps.add(QueryOperations.eq(QueryOperations.val(stringValue)));
		return this;
	}

    /**
     * Not equal comparison operator.
     *
     * @return Query query
     */
	public Query ne() {
		this.querySteps.add(QueryOperations.ne());
		return this;
	}

    /**
     * Not equal comparison operator.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public Query ne(Query otherQuery) {
		this.querySteps.add(QueryOperations.ne(otherQuery));
		return this;
	}

    /**
     * Not equal comparison operator.
     *
     * @param numberValue the number value
     * @return Query query
     */
	public Query ne(Number numberValue) {
		this.querySteps.add(QueryOperations.ne(QueryOperations.val(numberValue)));
		return this;
	}

    /**
     * Not equal comparison operator.
     *
     * @param booleanValue the boolean value
     * @return Query query
     */
	public Query ne(boolean booleanValue) {
		this.querySteps.add(QueryOperations.ne(QueryOperations.val(booleanValue)));
		return this;
	}

    /**
     * Not equal comparison operator.
     *
     * @param stringValue the string value
     * @return Query query
     */
	public Query ne(String stringValue) {
		this.querySteps.add(QueryOperations.ne(QueryOperations.val(stringValue)));
		return this;
	}

	/****** Arithmetic Operators ******/

    /**
     * Add operator.
     *
     * @return Query query
     */
	public Query add() {
		this.querySteps.add(QueryOperations.add());
		return this;
	}

    /**
     * Add operator.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public Query add(Query otherQuery) {
		this.querySteps.add(QueryOperations.add(otherQuery));
		return this;
	}

    /**
     * Add operator.
     *
     * @param val the val
     * @return Query query
     */
	public Query add(Number val) {
		this.querySteps.add(QueryOperations.add(val));
		return this;
	}

    /**
     * Subtract operator.
     *
     * @return Query query
     */
	public Query sub() {
		this.querySteps.add(QueryOperations.sub());
		return this;
	}

    /**
     * Subtract operator.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public Query sub(Query otherQuery) {
		this.querySteps.add(QueryOperations.sub(otherQuery));
		return this;
	}

    /**
     * Subtract operator.
     *
     * @param val the val
     * @return Query query
     */
	public Query sub(Number val) {
		this.querySteps.add(QueryOperations.sub(val));
		return this;
	}

    /**
     * Multiply operator.
     *
     * @return Query query
     */
	public Query mul() {
		this.querySteps.add(QueryOperations.mul());
		return this;
	}

    /**
     * Multiply operator.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public Query mul(Query otherQuery) {
		this.querySteps.add(QueryOperations.mul(otherQuery));
		return this;
	}

    /**
     * Multiply operator.
     *
     * @param val the val
     * @return Query query
     */
	public Query mul(Number val) {
		this.querySteps.add(QueryOperations.mul(val));
		return this;
	}

    /**
     * Divide operator.
     *
     * @return Query query
     */
	public Query div() {
		this.querySteps.add(QueryOperations.div());
		return this;
	}

    /**
     * Divide operator.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public Query div(Query otherQuery) {
		this.querySteps.add(QueryOperations.div(otherQuery));
		return this;
	}

    /**
     * Divide operator.
     *
     * @param val the val
     * @return Query query
     */
	public Query div(Number val) {
		this.querySteps.add(QueryOperations.div(val));
		return this;
	}

    /**
     * Reminder (or modulo) operator.
     *
     * @return Query query
     */
	public Query mod() {
		this.querySteps.add(QueryOperations.mod());
		return this;
	}

    /**
     * Reminder (or modulo) operator.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public Query mod(Query otherQuery) {
		this.querySteps.add(QueryOperations.mod(otherQuery));
		return this;
	}

    /**
     * Reminder (or modulo) operator.
     *
     * @param val the val
     * @return Query query
     */
	public Query mod(Number val) {
		this.querySteps.add(QueryOperations.mod(val));
		return this;
	}

	/****** Date Operators ******/

    /**
     * The year component value of the parameter value.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public Query year(Query otherQuery) {
		this.querySteps.add(QueryOperations.year(otherQuery));
		return this;
	}

    /**
     * The year component value of the parameter value.
     *
     * @param field the field
     * @return Query query
     */
	public Query year(String field) {
		this.querySteps.add(QueryOperations.year(field));
		return this;
	}

    /**
     * The month component value of the parameter value.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public Query month(Query otherQuery) {
		this.querySteps.add(QueryOperations.month(otherQuery));
		return this;
	}

    /**
     * The month component value of the parameter value.
     *
     * @param field the field
     * @return Query query
     */
	public Query month(String field) {
		this.querySteps.add(QueryOperations.month(field));
		return this;
	}

    /**
     * The day component value of the parameter value.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public Query day(Query otherQuery) {
		this.querySteps.add(QueryOperations.day(otherQuery));
		return this;
	}

    /**
     * The day component value of the parameter value.
     *
     * @param field the field
     * @return Query query
     */
	public Query day(String field) {
		this.querySteps.add(QueryOperations.day(field));
		return this;
	}

    /**
     * The hour component value of the parameter value.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public Query hour(Query otherQuery) {
		this.querySteps.add(QueryOperations.hour(otherQuery));
		return this;
	}

    /**
     * The hour component value of the parameter value.
     *
     * @param field the field
     * @return Query query
     */
	public Query hour(String field) {
		this.querySteps.add(QueryOperations.hour(field));
		return this;
	}

    /**
     * The minute component value of the parameter value.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public Query minute(Query otherQuery) {
		this.querySteps.add(QueryOperations.minute(otherQuery));
		return this;
	}

    /**
     * The minute component value of the parameter value.
     *
     * @param field the field
     * @return Query query
     */
	public Query minute(String field) {
		this.querySteps.add(QueryOperations.minute(field));
		return this;
	}

    /**
     * The second component value of the parameter value.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public Query second(Query otherQuery) {
		this.querySteps.add(QueryOperations.second(otherQuery));
		return this;
	}

    /**
     * The second component value of the parameter value.
     *
     * @param field the field
     * @return Query query
     */
	public Query second(String field) {
		this.querySteps.add(QueryOperations.second(field));
		return this;
	}

	/****** Math Functions ******/

    /**
     * The largest integral value less than or equal to the parameter value.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public Query floor(Query otherQuery) {
		this.querySteps.add(QueryOperations.floor(otherQuery));
		return this;
	}

    /**
     * The smallest integral value greater than or equal to the parameter value.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public Query ceiling(Query otherQuery) {
		this.querySteps.add(QueryOperations.ceiling(otherQuery));
		return this;
	}

    /**
     * The nearest integral value to the parameter value.
     *
     * @param otherQuery the other query
     * @return Query query
     */
	public Query round(Query otherQuery) {
		this.querySteps.add(QueryOperations.round(otherQuery));
		return this;
	}

	/****** String Operators ******/

    /**
     * String value with the contents of the parameter value converted to lower
     * case.
     *
     * @param exp the exp
     * @return Query query
     */
	public Query toLower(Query exp) {
		this.querySteps.add(QueryOperations.toLower(exp));
		return this;
	}

    /**
     * String value with the contents of the parameter value converted to lower
     * case.
     *
     * @param field the field
     * @return Query query
     */
	public Query toLower(String field) {
		this.querySteps.add(QueryOperations.toLower(field));
		return this;
	}

    /**
     * String value with the contents of the parameter value converted to upper
     * case.
     *
     * @param exp the exp
     * @return Query query
     */
	public Query toUpper(Query exp) {
		this.querySteps.add(QueryOperations.toUpper(exp));
		return this;
	}

    /**
     * String value with the contents of the parameter value converted to upper
     * case.
     *
     * @param field the field
     * @return Query query
     */
	public Query toUpper(String field) {
		this.querySteps.add(QueryOperations.toUpper(field));
		return this;
	}

    /**
     * The number of characters in the specified parameter value.
     *
     * @param exp the exp
     * @return Query query
     */
	public Query length(Query exp) {
		this.querySteps.add(QueryOperations.length(exp));
		return this;
	}

    /**
     * The number of characters in the specified parameter value.
     *
     * @param field the field
     * @return Query query
     */
	public Query length(String field) {
		this.querySteps.add(QueryOperations.length(field));
		return this;
	}

    /**
     * String value with the contents of the parameter value with all leading
     * and trailing white-space characters removed.
     *
     * @param exp the exp
     * @return Query query
     */
	public Query trim(Query exp) {
		this.querySteps.add(QueryOperations.trim(exp));
		return this;
	}

    /**
     * String value with the contents of the parameter value with all leading
     * and trailing white-space characters removed.
     *
     * @param field the field
     * @return Query query
     */
	public Query trim(String field) {
		this.querySteps.add(QueryOperations.trim(field));
		return this;
	}

    /**
     * Whether the beginning of the first parameter values matches the second
     * parameter value.
     *
     * @param field The field to evaluate
     * @param start Start value
     * @return Query query
     */
	public Query startsWith(Query field, Query start) {
		this.querySteps.add(QueryOperations.startsWith(field, start));
		return this;
	}

    /**
     * Whether the beginning of the first parameter values matches the second
     * parameter value.
     *
     * @param field The field to evaluate
     * @param start Start value
     * @return Query query
     */
	public Query startsWith(String field, String start) {
		this.querySteps.add(QueryOperations.startsWith(field, start));
		return this;
	}

    /**
     * Whether the end of the first parameter value matches the second parameter
     * value.
     *
     * @param field The field to evaluate
     * @param end End value
     * @return Query query
     */
	public Query endsWith(Query field, Query end) {
		this.querySteps.add(QueryOperations.endsWith(field, end));
		return this;
	}

    /**
     * Whether the end of the first parameter value matches the second parameter
     * value.
     *
     * @param field The field to evaluate
     * @param end End value
     * @return Query query
     */
	public Query endsWith(String field, String end) {
		this.querySteps.add(QueryOperations.endsWith(field, end));
		return this;
	}

    /**
     * Whether the first parameter string value occurs in the second parameter
     * string value.
     *
     * @param str1 First string
     * @param str2 Second string
     * @return Query query
     */
	public Query subStringOf(Query str1, Query str2) {
		this.querySteps.add(QueryOperations.subStringOf(str1, str2));
		return this;
	}

    /**
     * Whether the string parameter occurs in the field
     *
     * @param str the str
     * @param field Field to search in
     * @return Query query
     */
	public Query subStringOf(String str, String field) {
		this.querySteps.add(QueryOperations.subStringOf(str, field));
		return this;
	}

    /**
     * String value which is the first and second parameter values merged
     * together with the first parameter value coming first in the result.
     *
     * @param str1 First string
     * @param str2 Second string
     * @return Query query
     */
	public Query concat(Query str1, Query str2) {
		this.querySteps.add(QueryOperations.concat(str1, str2));
		return this;
	}

    /**
     * Index of the first occurrence of the second parameter value in the first
     * parameter value or -1 otherwise.
     *
     * @param haystack String content
     * @param needle Value to search for
     * @return Query query
     */
	public Query indexOf(Query haystack, Query needle) {
		this.querySteps.add(QueryOperations.indexOf(haystack, needle));
		return this;
	}

    /**
     * Index of the first occurrence of the second parameter value in the first
     * parameter value or -1 otherwise.
     *
     * @param field Field to search in
     * @param needle the needle
     * @return Query query
     */
	public Query indexOf(String field, String needle) {
		this.querySteps.add(QueryOperations.indexOf(field, needle));
		return this;
	}

    /**
     * String value starting at the character index specified by the second
     * parameter value in the first parameter string value.
     *
     * @param str String content
     * @param pos Starting position
     * @return Query query
     */
	public Query subString(Query str, Query pos) {
		this.querySteps.add(QueryOperations.subString(str, pos));
		return this;
	}

    /**
     * String value starting at the character index specified by the second
     * parameter value in the first parameter string value.
     *
     * @param field Field to scan
     * @param pos Starting position
     * @return Query query
     */
	public Query subString(String field, int pos) {
		this.querySteps.add(QueryOperations.subString(field, pos));
		return this;
	}

    /**
     * String value starting at the character index specified by the second
     * parameter value in the first parameter string value.
     *
     * @param str String content
     * @param pos Starting position
     * @param length Length
     * @return Query query
     */
	public Query subString(Query str, Query pos, Query length) {
		this.querySteps.add(QueryOperations.subString(str, pos, length));
		return this;
	}

    /**
     * String value starting at the character index specified by the second
     * parameter value in the first parameter string value.
     *
     * @param field Field to scan
     * @param pos Starting position
     * @param length Length
     * @return Query query
     */
	public Query subString(String field, int pos, int length) {
		this.querySteps.add(QueryOperations.subString(field, pos, length));
		return this;
	}

    /**
     * Finds the second string parameter in the first parameter string value and
     * replaces it with the third parameter value.
     *
     * @param str String content
     * @param find Search value
     * @param replace Replace value
     * @return Query query
     */
	public Query replace(Query str, Query find, Query replace) {
		this.querySteps.add(QueryOperations.replace(str, find, replace));
		return this;
	}

    /**
     * Finds the second string parameter in the first parameter string value and
     * replaces it with the third parameter value.
     *
     * @param field Field to scan
     * @param find Search value
     * @param replace Replace value
     * @return Query query
     */
	public Query replace(String field, String find, String replace) {
		this.querySteps.add(QueryOperations.replace(field, find, replace));
		return this;
	}
}
