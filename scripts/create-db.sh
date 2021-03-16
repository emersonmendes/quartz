#!/bin/bash

set -e

psql --quiet -c "ALTER USER postgres PASSWORD 'postgres'"
#
#psql --quiet -c "create table qrtz_triggers (  );"

